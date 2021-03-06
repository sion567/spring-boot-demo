package cc.sion.core.sys.biz.impl;

import cc.sion.core.sys.biz.*;
import cc.sion.core.sys.domain.*;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserAuthServiceImpl implements IUserAuthService {
    @Autowired
    private ISysGroupService sysGroupService;
    @Autowired
    private ISysAuthService sysAuthService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysResourceService sysResourceService;
    @Autowired
    private ISysPermissionService sysPermissionService;

    public Set<SysRole> findRoles(SysUser user) {
        if (user == null) {
            return Sets.newHashSet();
        }
        String userId = user.getId();
        log.debug("[findRoles]userID:{}",userId);
        //默认分组 + 根据用户编号 和 组织编号 找 分组
        Set<String> groupIds = sysGroupService.findShowGroupIds(userId);
        log.debug("[findRoles]groupIDs:{}",groupIds);
        //获取权限
        Set<String> roleIds = sysAuthService.findRoleIds(userId, groupIds);
        log.debug("[findRoles]roleIDs:{}",roleIds);
        return sysRoleService.findShowRoles(roleIds);
    }

    public Set<String> findStringRoles(SysUser user) {
//        Set<SysRole> roles = ((IUserAuthService) AopContext.currentProxy()).findRoles(user);
        Set<SysRole> roles = findRoles(user);
        return Sets.newHashSet(Collections2.transform(roles, new Function<SysRole, String>() {
            @Override
            public String apply(SysRole input) {
                return input.getRole();
            }
        }));
    }

    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public Set<String> findStringPermissions(SysUser user) {
        Set<String> permissions = Sets.newHashSet();

//        Set<SysRole> roles = ((IUserAuthService) AopContext.currentProxy()).findRoles(user);
        Set<SysRole> roles = findRoles(user);
        for (SysRole role : roles) {
            for (SysRoleResourcePermission sys_rrp : role.getResourcePermissions()) {
                SysResource resource = sysResourceService.getObj(sys_rrp.getResourceId());
                if (resource == null)
                    continue;

                log.debug("resource info:{}",resource);
                String actualResourceIdentity = sysResourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (StringUtils.isEmpty(actualResourceIdentity) || Boolean.FALSE.equals(resource.getShow())) {
                    continue;
                }

                for (String permissionId : sys_rrp.getPermissionIds()) {
                    SysPermission permission = sysPermissionService.getObj(permissionId);
                    //不可用
                    if (permission == null || Boolean.FALSE.equals(permission.getShow())) {
                        continue;
                    }
                    permissions.add(actualResourceIdentity + ":" + permission.getPermission());
                }
            }
        }
        return permissions;
    }

}
