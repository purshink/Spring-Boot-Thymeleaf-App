package com.example.hobbie.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entries")
public class Entry extends BaseEntity{

    private Date date;
    private String notes;

    public Entry() {
    }

    public Date getDate() {
        return date;
    }
    @Column(nullable = false)
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(columnDefinition = "TEXT")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
