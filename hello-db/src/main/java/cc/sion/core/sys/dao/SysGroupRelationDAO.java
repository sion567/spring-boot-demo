package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysGroupRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface SysGroupRelationDAO extends BaseRepository<SysGroupRelation,String> {
    SysGroupRelation findByGroupIdAndUserId(String groupId, String userId);

    @Query("select groupId from SysGroupRelation where userId=?1")
    List<String> findGroupIds(String userId);


    //无需删除用户 因为用户并不逻辑删除
    @Modifying
    @Query("delete from SysGroupRelation r where " +
            "not exists (select 1 from SysGroup g where r.groupId = g.id)")
    void clearDeletedGroupRelation();
}
