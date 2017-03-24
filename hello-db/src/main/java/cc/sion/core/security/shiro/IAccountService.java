package cc.sion.core.security.shiro;

import java.util.List;

public interface IAccountService {
    String HASH_ALGORITHM = "SHA-1";
    int HASH_INTERATIONS = 1024;
    int SALT_SIZE = 8;

    List<SysUser> getAllUser();

    SysUser getUser(Long id);

    SysUser findUserByLoginName(String loginName);

    void registerUser(SysUser user);

    void updateUser(SysUser user);

    void deleteUser(Long id);
}
