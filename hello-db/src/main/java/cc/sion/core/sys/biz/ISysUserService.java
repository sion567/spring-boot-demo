package cc.sion.core.sys.biz;

import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysUser;
import cc.sion.core.sys.domain.UserStatus;

import java.util.Map;
import java.util.Set;

public interface ISysUserService extends IBaseBiz<SysUser,String> {

    int SALT_SIZE = 8;

    SysUser saveUser(SysUser obj);
    SysUser findByEmail(String val);
    SysUser findByUserName(String val);
    SysUser findByMobilePhoneNumber(String val);

    SysUser changePassword(SysUser user, String newPassword);
    void changePassword(SysUser opUser, String[] ids, String newPassword);
    SysUser changeStatus(SysUser opUser, SysUser user, UserStatus newStatus, String reason);
    void changeStatus(SysUser opUser, String[] ids, UserStatus newStatus, String reason);
    SysUser login(String username, String password);

    Set<Map<String, Object>> findIdAndNames(String username);
}
