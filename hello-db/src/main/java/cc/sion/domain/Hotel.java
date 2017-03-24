package cc.sion.domain;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.persistence.*;

import cc.sion.core.entity.Entity348;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
@NoArgsConstructor
public class Hotel extends Entity348 {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NaturalId
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String zip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<Review> reviews;

    private int state;

}