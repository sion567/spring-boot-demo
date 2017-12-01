package cc.sion.core.sys.domain;

import cc.sion.core.entity.Entity348;
import cc.sion.core.entity.ZTreeable;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_resource")
public class SysResource extends Entity348 implements ZTreeable<String> {

    private String name;
    /**
     * 资源标识符 用于权限匹配的 如sys:resource
     */
    private String identity;
    /**
     * 点击后前往的地址
     * 菜单才有
     */
    private String url;
    /**
     * 父路径
     */
    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    private Integer weight;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否有叶子节点
     */
    @Formula(value = "(select count(*) from sys_resource s_r where s_r.parent_id = id)")
    private boolean hasChildren;
    /**
     * 是否显示
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;

    @Override
    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }

    public String getTreetableIds() {
        String selfId = makeSelfAsNewParentIds().replace("/", "-");
        return selfId.substring(0, selfId.length() - 1);
    }

    public String getTreetableParentIds() {
        String parentIds = getParentIds().replace("/", "-");
        return parentIds.substring(0, parentIds.length() - 1);
    }

    @Override
    public String getSeparator() {
        return "/";
    }

    public String getIcon() {
        if (!StringUtils.isEmpty(icon)) {
            return icon;
        }
        if (isRoot()) {
            return getRootDefaultIcon();
        }
        if (isLeaf()) {
            return getLeafDefaultIcon();
        }
        return getBranchDefaultIcon();
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public boolean isRoot() {
        if (getParentId() != null && !"".equals(getParentId())) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isLeaf() {
        if (isRoot()) {
            return false;
        }
        if (isHasChildren()) {
            return false;
        }
        return true;
    }

}
