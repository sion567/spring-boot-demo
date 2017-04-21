package cc.sion.core.sys.biz.impl;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysUserService;
import cc.sion.core.sys.biz.ISysUserStatusHistoryService;
import cc.sion.core.sys.dao.SysUserDAO;
import cc.sion.core.sys.domain.SysUser;
import cc.sion.core.sys.domain.UserStatus;
import cc.sion.core.sys.dto.ShiroUser;
import cc.sion.core.sys.security.LoginCacheManager;
import cc.sion.core.sys.security.exception.*;
import cc.sion.core.utils.LogUtils;
import cc.sion.utils.IpUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class SysUserServiceImpl extends BaseBizImpl<SysUser,String> implements ISysUserService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Autowired
    private ISysUserStatusHistoryService sysUserStatusHistoryService;
    @Autowired
    private PasswordService passwordService;

    //    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 5;

    @Override
    public SysUser login(String name, String password) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            log(name,"loginError", "username is empty");
            throw new UserNotExistsException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < SysUser.PASSWORD_MIN_LENGTH || password.length() > SysUser.PASSWORD_MAX_LENGTH) {
            log(
                    name,
                    "loginError",
                    "password length error! password is between {} and {}",
                    SysUser.PASSWORD_MIN_LENGTH, SysUser.PASSWORD_MAX_LENGTH);

            throw new UserPasswordNotMatchException();
        }

        SysUser user = null;

        //此处需要走代理对象，目的是能走缓存切面
//        ISysUserService proxyUserService = (ISysUserService) AopContext.currentProxy();
//        if (maybeUsername(name))
//            user = proxyUserService.findByUserName(name);
//        if (user == null && maybeEmail(name))
//            user = proxyUserService.findByEmail(name);
//        if (user == null && maybeMobilePhoneNumber(name))
//            user = proxyUserService.findByMobilePhoneNumber(name);
        if (maybeUsername(name))
            user = findByUserName(name);
        if (user == null && maybeEmail(name))
            user = findByEmail(name);
        if (user == null && maybeMobilePhoneNumber(name))
            user = findByMobilePhoneNumber(name);

        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            log(
                    name,
                    "loginError",
                    "user is not exists!");

            throw new UserNotExistsException();
        }

        validate(user, password);
        log.debug("validate user success.");
        if (user.getStatus() == UserStatus.blocked) {
            log(
                    name,
                    "loginError",
                    "user is blocked!");
            throw new UserBlockedException(sysUserStatusHistoryService.getLastReason(user));
        }
        log(
                name,
                "loginSuccess",
                "");
        return user;
    }

    public void validate(SysUser user, String password) {
        String name = user.getUserName();
        Integer retryCount = LoginCacheManager.getCache(name);
        log.debug("retryCount:{}",retryCount);
        if (retryCount >= maxRetryCount) {
            log(
                    name,
                    "passwordError",
                    "password error, retry limit exceed! password: {},max retry count {}",
                    password, maxRetryCount);
            throw new UserPasswordRetryLimitExceedException(maxRetryCount);
        }
        log.debug("pwd1:{}",user.getPassword());
        log.debug("pwd2:{}",password);
        if (!passwordService.passwordsMatch(password,user.getPassword())) {
            LoginCacheManager.setCache(name, ++retryCount);
            log(
                    name,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            LoginCacheManager.removeCache(name);
        }
    }


    /**
     * 判断是否超级管理员.
     */
    protected boolean isSupervisor(SysUser obj) {
        return obj.getFlag() == 9;
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    protected String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.loginName;
    }



    @Override
    public SysUser saveUser(SysUser obj) {
        if (obj.getRegisterDate() == null)
            obj.setRegisterDate(new Date());
        obj.setPassword(passwordService.encryptPassword(obj.getPlainPassword()));
        return super.save(obj);
    }

    @Override
    public SysUser findByEmail(String val) {
        if(StringUtils.isEmpty(val)) {
            return null;
        }
        return sysUserDAO.findByEmail(val);
    }

    @Override
    public SysUser findByUserName(String val) {
        if(StringUtils.isEmpty(val)) {
            return null;
        }
        return sysUserDAO.findByUserName(val);
    }

    @Override
    public SysUser findByMobilePhoneNumber(String val) {
        if(StringUtils.isEmpty(val)) {
            return null;
        }
        return sysUserDAO.findByMobilePhoneNumber(val);
    }

    @Override
    public SysUser changePassword(SysUser user, String newPassword) {
        user.setPassword(passwordService.encryptPassword(newPassword));
        return saveUser(user);
    }
    public void changePassword(SysUser opUser, String[] ids, String newPassword) {
//        ISysUserService proxyUserService = (ISysUserService) AopContext.currentProxy();
        for (String id : ids) {
            SysUser user = getObj(id);
//            proxyUserService.changePassword(user, newPassword);
            changePassword(user, newPassword);
            log(
                    user.getUserName(),
                    "changePassword",
                    "admin user {} change password!", opUser.getUserName());

        }
    }

    @Override
    public SysUser changeStatus(SysUser opUser, SysUser user, UserStatus newStatus, String reason) {
        user.setStatus(newStatus);
        saveUser(user);
        sysUserStatusHistoryService.log(opUser, user, newStatus, reason);
        return user;
    }
    public void changeStatus(SysUser opUser, String[] ids, UserStatus newStatus, String reason) {
//        ISysUserService proxyUserService = (ISysUserService) AopContext.currentProxy();
        for (String id : ids) {
            SysUser user = getObj(id);
//            proxyUserService.changeStatus(opUser, user, newStatus, reason);
            changeStatus(opUser, user, newStatus, reason);
            log(
                    user.getUserName(),
                    "changeStatus",
                    "admin user {} change status!", opUser.getUserName());
        }
    }

    /**
     * 干啥子滴呢？？？？
     * @param username
     * @return
     */
    public Set<Map<String, Object>> findIdAndNames(String username) {

        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("LIKE_username",username);
        searchParams.put("EQ_deleted",false);

        return Sets.newHashSet(
                Lists.transform(
                        findAll(searchParams),
                        new Function<SysUser, Map<String, Object>>() {
                            @Override
                            public Map<String, Object> apply(SysUser input) {
                                Map<String, Object> data = Maps.newHashMap();
                                data.put("label", input.getUserName());
                                data.put("value", input.getId());
                                return data;
                            }
                        }
                )
        );
    }







    private boolean maybeUsername(String username) {
        if (!username.matches(SysUser.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < SysUser.USERNAME_MIN_LENGTH || username.length() > SysUser.USERNAME_MAX_LENGTH) {
            return false;
        }
        return true;
    }
    private boolean maybeEmail(String username) {
        if (!username.matches(SysUser.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }
    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(SysUser.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }
    public static void log(String username, String op, String msg, Object... args) {
        StringBuilder s = new StringBuilder();
        s.append(LogUtils.getBlock(getIp()));
        s.append(LogUtils.getBlock(username));
        s.append(LogUtils.getBlock(op));
        s.append(LogUtils.getBlock(msg));
        log.info(s.toString(), args);
    }
    public static Object getIp() {
        RequestAttributes requestAttributes = null;
        try {
            requestAttributes = RequestContextHolder.currentRequestAttributes();
        } catch (Exception e) {
            //FIXME:ignore  如unit test
        }
        if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
            return IpUtils.getIpAddr(((ServletRequestAttributes) requestAttributes).getRequest());
        }
        return "unknown";
    }

}
