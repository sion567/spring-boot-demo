package cc.sion.core.sys.biz.impl;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysGroupRelationService;
import cc.sion.core.sys.biz.ISysGroupService;
import cc.sion.core.sys.dao.SysGroupDAO;
import cc.sion.core.sys.domain.SysGroup;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class SysGroupServiceImpl extends BaseBizImpl<SysGroup,String> implements ISysGroupService {
    @Autowired
    private SysGroupDAO sysGroupDAO;
    /**
     * 获取可用的的分组编号列表
     *
     * @param userId
     * @return
     */
    public Set<String> findShowGroupIds(String userId) {
        Set<String> groupIds = Sets.newHashSet();
        groupIds.addAll(sysGroupDAO.findDefaultGroupIds());

        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("EQ_show",false);
        for (SysGroup group : findAll(searchParams)) {
            groupIds.remove(group.getId());
        }

        return groupIds;
    }

}
