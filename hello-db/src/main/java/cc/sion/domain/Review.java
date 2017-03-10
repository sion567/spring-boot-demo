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

import org.springframework.util.Assert;

@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

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

    public Review() {
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public int getIndex() {
        return this.index;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}