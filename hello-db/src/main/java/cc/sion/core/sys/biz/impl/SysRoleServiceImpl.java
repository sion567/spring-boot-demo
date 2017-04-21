package cc.sion.core.sys.biz.impl;


import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysRoleService;
import cc.sion.core.sys.domain.SysRole;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysRoleServiceImpl extends BaseBizImpl<SysRole,String> implements ISysRoleService {
    @Override
    public Set<SysRole> findShowRoles(Set<String> roleIds) {
        Set<SysRole> roles = Sets.newHashSet();

        for (SysRole role : findAll(Collections.singletonMap("EQ_show",true))) {
            //??为什么不行
//            if (roleIds.contains(role.getId())) {
//                roles.add(role);
//            }
            for(String rId : roleIds)
                if(rId.equals(role.getId()))
                    roles.add(role);
        }
        log.debug("findShowRoles size:{}",roles.size());
        return roles;
    }


}
