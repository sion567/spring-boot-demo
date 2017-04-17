package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_user_status_history")
public class SysUserStatusHistory extends Entity348 {
    /**
     * 锁定的用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private SysUser user;

    /**
     * 备注信息
     */
    private String reason;

    /**
     * 操作的状态
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /**
     * 操作的管理员
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "op_user_id")
    private SysUser opUser;

}
