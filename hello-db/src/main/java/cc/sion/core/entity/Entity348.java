package cc.sion.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class Entity348 implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator="sys-uuid")
    @GenericGenerator(name="sys-uuid", strategy="uuid")
    @Column(name="id",length = 32)
    private String id;

    @Transient
    @JsonIgnore
    public final boolean isNew() {
        return null == this.getId();
    }

    @Column(
            name = "delete_state",
            columnDefinition = "INT default 0"
    )
    @JsonIgnore
    private int deleteState;
    @Column(
            name = "last_update_time",
            columnDefinition = "TIMESTAMP"
    )
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastUpdateTime;

}
