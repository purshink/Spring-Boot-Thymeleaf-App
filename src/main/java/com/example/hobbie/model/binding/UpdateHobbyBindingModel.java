package com.example.hobbie.model.binding;

import com.example.hobbie.model.entities.enums.CategoryNameEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UpdateHobbyBindingModel {
    private Long id;
    private String name;
    private String description;
    private CategoryNameEnum category;
    private String photos;
    private BigDecimal price;

    public UpdateHobbyBindingModel() {
    }


    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols.")
    @NotBlank(message = "Hobby name can not be empty.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 30,max = 1050 , message = "Description must be between 30 and 1050 symbols")
    @NotBlank(message = "You need to have a description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @NotNull(message = "You have to choose category")
    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    @NotNull(message = "You have to set price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
