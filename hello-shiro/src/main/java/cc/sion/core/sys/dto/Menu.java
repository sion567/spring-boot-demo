package cc.sion.core.sys.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    private String id;
    private String name;
    private String icon;
    private String url;
    private List<Menu> children;

    public Menu(String id, String name, String icon, String url) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    public List<Menu> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
    public boolean isHasChildren() {
        return !getChildren().isEmpty();
    }
}
