package cc.sion.core.sys.biz;


import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysAuth;

import java.util.List;
import java.util.Set;

public interface ISysAuthService extends IBaseBiz<SysAuth,String> {
    Set<String> findRoleIds(String userId, Set<String> groupIds);
}
