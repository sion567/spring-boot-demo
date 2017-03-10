package cc.sion.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String zip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<Review> reviews;

    protected Hotel() {
    }

    public Hotel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZip() {
        return this.zip;
    }
}