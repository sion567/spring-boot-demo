package cc.sion.core.sys.dao;

import cc.sion.core.repository.BaseRepository;
import cc.sion.core.sys.domain.SysGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SysGroupDAO extends BaseRepository<SysGroup,String> {
    @Query("select id from SysGroup where defaultGroup=true and show=true")
    List<String> findDefaultGroupIds();
}
