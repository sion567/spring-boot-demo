package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_permission")
public class SysPermission extends Entity348{
    /**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的权限标识
     */
    private String permission;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;
}
