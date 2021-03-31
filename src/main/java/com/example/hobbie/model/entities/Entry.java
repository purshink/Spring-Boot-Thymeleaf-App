package com.example.hobbie.model.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entries")
public class Entry extends BaseEntity{

    private Date date;
    private Abo abo;


    public Entry() {
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Abo getAbo() {
        return abo;
    }

    public void setAbo(Abo abo) {
        this.abo = abo;
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
