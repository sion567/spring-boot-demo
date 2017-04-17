package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import lombok.Data;

import javax.persistence.*;

/**
 * 分组与 用户/组织机构关系表
 * <p/>
 * 将用户/组织机构放一张表目的是提高查询性能
 * <p/>
 */
@Data
@Entity
@Table(name = "sys_group_relation")
public class SysGroupRelation extends Entity348 {
    @Column(name = "group_id")
    private String groupId;
    /**
     * 关联的单个用户
     */
    @Column(name = "user_id")
    private String userId;

}
