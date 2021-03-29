package com.example.hobbie.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entries")
public class Entry extends BaseEntity{
    private Long aboId;
    private Date date;


    public Entry() {
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @NotNull
    public Long getAboId() {
        return aboId;
    }

    public void setAboId(Long aboId) {
        this.aboId = aboId;
    }
}
