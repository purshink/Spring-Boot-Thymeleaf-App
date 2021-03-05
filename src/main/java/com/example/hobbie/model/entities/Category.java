package com.example.hobbie.model.entities;


import com.example.hobbie.model.entities.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    private CategoryNameEnum name;
    private String description;

    public Category(CategoryNameEnum categoryNameEnum, String description) {
        this.name = categoryNameEnum;
        this.description = description;
    }

    public Category() {

    }


    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}