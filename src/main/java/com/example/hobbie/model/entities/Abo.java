package com.example.hobbie.model.entities;

import com.example.hobbie.model.entities.enums.AboTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "abos")
public class Abo extends BaseEntity{
    private AppClient client;
    private Hobby hobby;
    private AboTypeEnum aboType;
    private List<Entry> entries;

    public Abo() {
    }



    @Column(name = "abo_type",unique = true)
    @Enumerated(EnumType.STRING)
    public AboTypeEnum getAboType() {
        return aboType;
    }

    public void setAboType(AboTypeEnum aboType) {
        this.aboType = aboType;
    }
    @OneToMany
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
    @OneToOne
    public AppClient getClient() {
        return client;
    }

    public void setClient(AppClient client) {
        this.client = client;
    }
    @ManyToOne
    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
}
