package com.example.hobbie.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hobbies")
public class Hobby extends BaseEntity{

    private String name;
    private String description;
    private Category category;
    private String imgUrl;
    private List<Abo> abos;


    public Hobby() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany
    public List<Abo> getAbos() {
        return abos;
    }

    public void setAbos(List<Abo> abos) {
        this.abos = abos;
    }

    @Column
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
