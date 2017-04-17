package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_user_last_online")
public class SysUserLastOnline extends Entity348 {
    /**
     * 在线的用户
     */
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    /**
     * 最后退出时的uid
     */
    private String uid;

    /**
     * 用户主机地址
     */
    @Column(name = "host")
    private String host;


    /**
     * 用户浏览器类型
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 用户登录时系统IP
     */
    @Column(name = "system_host")
    private String systemHost;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_login_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTimestamp;

    /**
     * 最后退出时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_stop_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastStopTimestamp;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private int loginCount;

    /**
     * 总的在线时长（秒为单位）
     */
    @Column(name = "total_online_time")
    private long totalOnlineTime;
}
