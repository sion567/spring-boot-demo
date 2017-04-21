package cc.sion.core.sys.biz;

import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysGroupRelation;

import java.util.List;
import java.util.Set;

public interface ISysGroupRelationService extends IBaseBiz<SysGroupRelation,String> {
    void appendRelation2User(String groupId, String[] userIds);
    Set<String> findGroupIds(String userId);
}
