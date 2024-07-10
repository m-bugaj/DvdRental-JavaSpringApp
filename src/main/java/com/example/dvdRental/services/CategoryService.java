package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> findAllCategories();

    Optional<CategoryDTO> findCategoryById(Integer categoryId);

    CategoryDTO createNewCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO);

    void deleteCategory(Integer categoryId);
}
