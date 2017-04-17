package cc.sion.core.sys.biz.impl;

import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysGroupRelationService;
import cc.sion.core.sys.dao.SysGroupRelationDAO;
import cc.sion.core.sys.domain.SysGroupRelation;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class SysGroupRelationServiceImpl extends BaseBizImpl<SysGroupRelation,String> implements ISysGroupRelationService {
    @Autowired
    private SysGroupRelationDAO sysGroupRelationDAO;

    public void appendRelation2User(String groupId, String[] userIds) {
        if (ArrayUtils.isEmpty(userIds)) {
            return;
        }
        for (String userId : userIds) {
            if (userId == null) {
                continue;
            }
            SysGroupRelation r = sysGroupRelationDAO.findByGroupIdAndUserId(groupId, userId);
            if (r == null) {
                r = new SysGroupRelation();
                r.setGroupId(groupId);
                r.setUserId(userId);
                save(r);
            }
        }
    }

    public Set<String> findGroupIds(String userId) {
        return Sets.newHashSet(sysGroupRelationDAO.findGroupIds(userId));
    }


}
