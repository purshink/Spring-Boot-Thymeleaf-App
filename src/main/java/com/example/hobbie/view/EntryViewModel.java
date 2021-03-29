package com.example.hobbie.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

public class EntryViewModel {
    private Long id;
    private Date date;
    private Long aboId;

    public EntryViewModel() {
    }

    public Long getAboId() {
        return aboId;
    }

    public void setAboId(Long abId) {
        this.aboId = abId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
