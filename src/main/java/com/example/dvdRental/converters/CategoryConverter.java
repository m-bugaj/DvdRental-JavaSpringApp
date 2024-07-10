package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CategoryDTO;
import com.example.dvdRental.model.Category;

public class CategoryConverter {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        categoryDTO.setLastUpdate(category.getLastUpdate());

        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        category.setLastUpdate(categoryDTO.getLastUpdate());
        return category;
    }
}
