package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_group")
public class SysGroup extends Entity348 {
    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组类型 如 用户分组/组织机构分组
     */
    @Enumerated(EnumType.STRING)
    private GroupType type;

    /**
     * 是否是默认分组
     */
    @Column(name = "default_group")
    private Boolean defaultGroup = Boolean.FALSE;

    /**
     * 是否显示/可用
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;
}
