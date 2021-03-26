package com.example.hobbie.model.service;

import com.example.hobbie.model.entities.enums.CategoryNameEnum;
import com.example.hobbie.model.entities.enums.LocationEnum;

import java.math.BigDecimal;

public class UpdateHobbyServiceModel {
    private Long id;
    private String name;
    private String description;
    private CategoryNameEnum category;
    private String photos;
    private BigDecimal price;
    private LocationEnum location;


    public UpdateHobbyServiceModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationEnum getLocation() {
        return location;
    }

    public void setLocation(LocationEnum location) {
        this.location = location;
    }
}