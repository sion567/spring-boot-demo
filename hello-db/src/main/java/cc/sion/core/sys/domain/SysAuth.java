package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import cc.sion.core.sys.domain.type.CollectionToStringUserType;
import com.google.common.collect.Sets;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Set;

/**
 * 组织机构 工作职位  用户  角色 关系表
 * 1、授权的五种情况
 * 只给组织机构授权 (orgnizationId=? and jobId=0)
 * 只给工作职务授权 (orgnizationId=0 and jobId=?)
 * 给组织机构和工作职务都授权 (orgnizationId=? and jobId=?)
 * 给用户授权  (userId=?)
 * 给组授权 (groupId=?)
 * <p/>
 * 因此查询用户有没有权限 就是
 * where (orgnizationId=? and jobId=0) or (organizationId = 0 and jobId=?) or (orgnizationId=? and jobId=?) or (userId=?) or (groupId=?)
 * <p/>
 * <p/>
 * 2、为了提高性能
 * 放到一张表
 * 此处不做关系映射（这样需要配合缓存）
 * <p/>
 * 3、如果另一方是可选的（如只选组织机构 或 只选工作职务） 那么默认0 使用0的目的是为了也让走索引
 * <p/>
 */
@Data
@TypeDef(
        name = "SetToStringUserType",
        typeClass = CollectionToStringUserType.class,
        parameters = {
                @Parameter(name = "separator", value = ","),
                @Parameter(name = "collectionType", value = "java.util.HashSet"),
                @Parameter(name = "elementType", value = "java.lang.String")
        }
)
@Entity
@Table(name = "sys_auth")
public class SysAuth extends Entity348 {
    /**
     * 用户
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 组
     */
    @Column(name = "group_id")
    private String groupId;

    @Type(type = "SetToStringUserType")
    @Column(name = "role_ids")
    private Set<String> roleIds;

    @Enumerated(EnumType.STRING)
    private AuthType type;
    public Set<String> getRoleIds() {
        if (roleIds == null) {
            roleIds = Sets.newHashSet();
        }
        return roleIds;
    }
    public void addRoleId(String roleId) {
        getRoleIds().add(roleId);
    }
    public void addRoleIds(Set<String> roleIds) {
        getRoleIds().addAll(roleIds);
    }

}
