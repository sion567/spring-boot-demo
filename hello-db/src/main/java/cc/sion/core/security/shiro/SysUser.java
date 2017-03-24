package cc.sion.core.security.shiro;

import cc.sion.core.utils.Collections3;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SysUser implements Serializable{
    private Long id;
    private String loginName;
    private String name;
    private String plainPassword;
    private String password;
    private String salt;
    private String roles;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date registerDate;
    private String email;

    @Transient
    @JsonIgnore
    public List<String> getRoleList() {
        // 角色列表在数据库中实际以逗号分隔字符串存储，因此返回不能修改的List.
        return Collections3.copyOf(StringUtils.split(roles, ","));
    }


    @Transient
    @JsonIgnore
    public String getCredentialSalt() {
        return loginName+salt;
    }

}
