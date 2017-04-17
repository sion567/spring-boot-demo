package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysUserLastOnline;


public interface SysUserLastOnlineDAO extends BaseRepository<SysUserLastOnline,String> {
    SysUserLastOnline findByUserId(String userId);
}
