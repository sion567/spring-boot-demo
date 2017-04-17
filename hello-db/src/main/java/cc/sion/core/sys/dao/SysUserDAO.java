package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysUser;

public interface SysUserDAO extends BaseRepository<SysUser,String> {
    SysUser findByUserName(String v);
    SysUser findByEmail(String v);
    SysUser findByMobilePhoneNumber(String v);

}
