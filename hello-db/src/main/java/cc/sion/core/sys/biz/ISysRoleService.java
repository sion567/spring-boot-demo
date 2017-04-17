package cc.sion.core.sys.biz;

import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysRole;

import java.util.Set;


public interface ISysRoleService extends IBaseBiz<SysRole,String> {
    Set<SysRole> findShowRoles(Set<String> roleIds);

}
