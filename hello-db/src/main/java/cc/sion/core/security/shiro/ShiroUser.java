package cc.sion.core.security.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiroUser {
    private static final long serialVersionUID = -1373760761780840081L;
    public Long id;
    public String loginName;
    public String name;
}
