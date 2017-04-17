package cc.sion.core.sys.biz.impl;

import cc.sion.core.biz.BaseBizImpl;
import cc.sion.core.sys.biz.ISysUserStatusHistoryService;
import cc.sion.core.sys.domain.SysUser;
import cc.sion.core.sys.domain.SysUserStatusHistory;
import cc.sion.core.sys.domain.UserStatus;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class SysUserStatusHistoryServiceImpl extends BaseBizImpl<SysUserStatusHistory,String> implements ISysUserStatusHistoryService {

    public void log(SysUser opUser, SysUser user, UserStatus newStatus, String reason) {
        SysUserStatusHistory history = new SysUserStatusHistory();
        history.setUser(user);
        history.setOpUser(opUser);
        history.setStatus(newStatus);
        history.setReason(reason);
        save(history);
    }

    public SysUserStatusHistory findLastHistory(final SysUser user) {
        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("EQ_user",user);
        Page<SysUserStatusHistory> page = getByPage(searchParams ,1,1,"lastUpdateTime");
        if (page.hasContent()) {
            return page.getContent().get(0);
        }
        return null;
    }

    public String getLastReason(SysUser user) {
        SysUserStatusHistory history = findLastHistory(user);
        if (Objects.isNull(history)) {
            return "";
        }
        return history.getReason();
    }
}
