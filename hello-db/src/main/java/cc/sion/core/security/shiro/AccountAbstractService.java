package cc.sion.core.security.shiro;

import cc.sion.core.utils.Digests;
import cc.sion.core.utils.Encodes;
import org.apache.shiro.SecurityUtils;


public abstract class AccountAbstractService implements IAccountService {

    /**
     * 判断是否超级管理员.
     */
    protected boolean isSupervisor(Long id) {
        return id == 1;
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    protected String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.loginName;
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    protected void entryptPassword(SysUser user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

}