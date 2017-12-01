package cc.sion.core.sys.biz.impl;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysGroupRelationService;
import cc.sion.core.sys.biz.ISysGroupService;
import cc.sion.core.sys.dao.SysGroupDAO;
import cc.sion.core.sys.domain.SysGroup;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class SysGroupServiceImpl extends BaseBizImpl<SysGroup,String> implements ISysGroupService {
    @Autowired
    private ISysGroupRelationService sysGroupRelationService;
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
        groupIds.addAll(sysGroupRelationService.findGroupIds(userId));
        log.debug("findAllGroupIds size:{}",groupIds.size());
        for (SysGroup group : findAll(Collections.singletonMap("EQ_show",false))) {
            groupIds.remove(group.getId());
        }
        log.debug("findShowGroupIds size:{}",groupIds.size());
        return groupIds;
    }

}
