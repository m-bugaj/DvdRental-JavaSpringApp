package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CategoryDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.CategoryConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Category;
import com.example.dvdRental.model.Category;
import com.example.dvdRental.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDTO> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> findCategoryById(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) return optionalCategory.map(CategoryConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {
        Category category = CategoryConverter.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return CategoryConverter.toDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

    }
}
