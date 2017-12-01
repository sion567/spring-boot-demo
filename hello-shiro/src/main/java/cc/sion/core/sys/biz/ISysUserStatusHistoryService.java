package cc.sion.core.sys.biz;


import cc.sion.core.biz.IBaseBiz;
import cc.sion.core.sys.domain.SysUser;
import cc.sion.core.sys.domain.SysUserStatusHistory;
import cc.sion.core.sys.domain.UserStatus;

public interface ISysUserStatusHistoryService extends IBaseBiz<SysUserStatusHistory,String> {
    void log(SysUser opUser, SysUser user, UserStatus newStatus, String reason) ;
    SysUserStatusHistory findLastHistory(final SysUser user);
    String getLastReason(SysUser user);
}
