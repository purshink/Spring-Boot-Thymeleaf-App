package com.example.hobbie.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entries")
public class Entry extends BaseEntity{

    private Integer duration;
    private BigDecimal price;
    private Date date;

    public Entry() {
    }

    @Column(nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }
    @Column(nullable = false)
    public void setDate(Date date) {
        this.date = date;
    }
}
