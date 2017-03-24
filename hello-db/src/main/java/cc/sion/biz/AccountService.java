package cc.sion.biz;

import cc.sion.core.security.shiro.AccountAbstractService;
import cc.sion.core.security.shiro.SysUser;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component("accountService")
public class AccountService extends AccountAbstractService {

    private static final String USER_NAME = "admin";

    @Override public List<SysUser> getAllUser() {
        return null;
    }

    @Override public SysUser getUser(Long id) {
        return null;
    }

    @Override public SysUser findUserByLoginName(String loginName) {
        if(loginName.equals(USER_NAME)){
            SysUser user = new SysUser();
            user.setId(1L);
            user.setLoginName("admin");
            user.setSalt("86695131");
            user.setPassword("0131594bbf2c521f2920fbdc0b22e63719c70a09");//aa
            user.setRoles("admin");

            return user;
        }else{
            return null;
        }
    }

    @Override public void registerUser(SysUser user) {
        entryptPassword(user);
        user.setRoles("user");
        user.setRegisterDate(new Date());
//        userDao.save(user);
    }

    @Override public void updateUser(SysUser user) {

    }

    @Override public void deleteUser(Long id) {

    }

}
