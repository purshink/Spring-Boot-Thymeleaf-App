package com.example.hobbie.service;

import com.example.hobbie.model.entities.Category;
import com.example.hobbie.model.entities.enums.CategoryNameEnum;

public interface CategoryService {
    Category findByName(CategoryNameEnum category);

    void initCategories();

}
