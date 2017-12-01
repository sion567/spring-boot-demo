package cc.sion.core.sys.biz;

import cc.sion.core.sys.domain.SysRole;
import cc.sion.core.sys.domain.SysUser;

import java.util.List;
import java.util.Set;

public interface IUserAuthService {

    Set<SysRole> findRoles(SysUser user);
    Set<String> findStringRoles(SysUser user);
    Set<String> findStringPermissions(SysUser user);
}
