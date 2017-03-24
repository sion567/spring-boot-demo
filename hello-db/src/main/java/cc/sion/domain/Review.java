package cc.sion.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cc.sion.core.entity.Entity348;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Data
@NoArgsConstructor
public class Review extends Entity348 {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    private Hotel hotel;

    @Column(nullable = false, name = "idx")
    private int index;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkInDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String details;

}