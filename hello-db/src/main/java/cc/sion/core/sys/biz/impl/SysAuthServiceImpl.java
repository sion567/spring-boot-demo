package cc.sion.core.sys.biz.impl;

import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysAuthService;
import cc.sion.core.sys.biz.ISysGroupService;
import cc.sion.core.sys.biz.ISysUserService;
import cc.sion.core.sys.dao.SysAuthDAO;
import cc.sion.core.sys.domain.SysAuth;
import cc.sion.core.sys.domain.SysGroup;
import cc.sion.core.sys.domain.SysUser;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysAuthServiceImpl extends BaseBizImpl<SysAuth,String> implements ISysAuthService {
    @Autowired
    private SysAuthDAO sysAuthDAO;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysGroupService sysGroupService;



    public void addUserAuth(String[] userIds, SysAuth m) {
        if (ArrayUtils.isEmpty(userIds)) {
            return;
        }
        for (String userId : userIds) {

            SysUser user = sysUserService.getObj(userId);
            if (user == null) {
                continue;
            }
            SysAuth auth = sysAuthDAO.findByUserId(userId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new SysAuth();
            auth.setUserId(userId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            save(auth);
        }
    }

    public void addGroupAuth(String[] groupIds, SysAuth m) {
        if (ArrayUtils.isEmpty(groupIds)) {
            return;
        }

        for (String groupId : groupIds) {
            SysGroup group = sysGroupService.getObj(groupId);
            if (group == null) {
                continue;
            }

            SysAuth auth = sysAuthDAO.findByGroupId(groupId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new SysAuth();
            auth.setGroupId(groupId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            save(auth);
        }
    }
    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.5、组  根据组绝对匹配
     *
     * @param userId             必须有
     * @param groupIds           可选
     * @return
     */
    @Override
    public Set<String> findRoleIds(String userId, Set<String> groupIds) {
        Set<String> set = Sets.newHashSet();
        List<SysAuth> list = sysAuthDAO.findRoleIds(userId, groupIds);
        for(SysAuth auth : list){
            set.addAll(auth.getRoleIds());
        }
        return set;
    }
}
