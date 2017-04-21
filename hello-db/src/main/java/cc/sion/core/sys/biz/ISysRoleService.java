package cc.sion.core.sys.biz;

import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysRole;

import java.util.List;
import java.util.Set;


public interface ISysRoleService extends IBaseBiz<SysRole,String> {
    /**
     *  获取可用的角色列表
     * @param roleIds
     * @return
     */
    Set<SysRole> findShowRoles(Set<String> roleIds);

}
