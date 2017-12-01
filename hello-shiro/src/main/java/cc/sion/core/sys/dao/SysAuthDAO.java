package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysAuth;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SysAuthDAO extends BaseRepository<SysAuth,String> {
    SysAuth findByUserId(String userId);
    SysAuth findByGroupId(String groupId);

    @Query("from SysAuth a where a.userId=?1 or (a.groupId in (?2)) ")
    List<SysAuth> findRoleIds(String userId, Set<String> groupIds);
}
