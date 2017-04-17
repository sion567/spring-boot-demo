package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysRole;
import cc.sion.core.sys.domain.SysRoleResourcePermission;
import org.springframework.data.jpa.repository.Query;


public interface SysRoleDAO extends BaseRepository<SysRole,String> {
    @Query("from SysRoleResourcePermission where role=?1 and resourceId=?2")
    SysRoleResourcePermission findRoleResourcePermission(SysRole role, String resourceId);
}
