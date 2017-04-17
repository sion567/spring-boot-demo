package cc.sion.core.sys.biz;


import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysGroup;

import java.util.Set;

public interface ISysGroupService extends IBaseBiz<SysGroup,String> {
    Set<String> findShowGroupIds(String userId);
}
