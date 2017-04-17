package cc.sion.core.sys.biz.impl;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysRoleService;
import cc.sion.core.sys.domain.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class SysRoleServiceImpl extends BaseBizImpl<SysRole,String> implements ISysRoleService {
    @Override
    public Set<SysRole> findShowRoles(Set<String> roleIds) {
        return null;
    }
}
