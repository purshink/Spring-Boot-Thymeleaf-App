package com.example.hobbie.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "hobbies")
public class Hobby extends BaseEntity {

    private String name;
    private String description;
    private Category category;
    ;
    private BusinessOwner businessOwner;
    private BigDecimal price;
    private Location location;
    private String imgUrl;

    public Hobby() {
    }

    @Column(name = "image_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    @ManyToOne
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
