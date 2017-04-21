package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_role")
public class SysRole extends Entity348{
    /**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的角色标识
     */
    private String role;

    /**
     * 详细描述
     */
    private String description;


    /**
     * 用户 组织机构 工作职务关联表
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SysRoleResourcePermission.class, mappedBy = "role", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @OrderBy
    private List<SysRoleResourcePermission> resourcePermissions ;

    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;

    public void addResourcePermission(SysRoleResourcePermission roleResourcePermission) {
        roleResourcePermission.setRole(this);
        getResourcePermissions().add(roleResourcePermission);
    }
    public List<SysRoleResourcePermission> getResourcePermissions() {
        if (resourcePermissions == null) {
            resourcePermissions = Lists.newArrayList();
        }
        return resourcePermissions;
    }
}
