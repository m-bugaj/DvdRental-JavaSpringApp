package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.CategoryDTO;
import com.example.dvdRental.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController {
    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> findAllCategoryes() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<CategoryDTO> findCategoryById(@PathVariable("id") Integer categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<CategoryDTO>(categoryService.createNewCategory(categoryDTO), HttpStatus.CREATED);
    }
}
