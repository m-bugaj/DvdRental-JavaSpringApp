package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CategoryDTO;
import com.example.dvdRental.api.model.FilmCategoryDTO;
import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.model.FilmCategory;

public class FilmCategoryConverter {
    public static FilmCategoryDTO toDTO(FilmCategory filmCategory) {
        FilmDTO filmDTO = FilmConverter.toDTO(filmCategory.getFilm());
        CategoryDTO categoryDTO = CategoryConverter.toDTO(filmCategory.getCategory());
        FilmCategoryDTO filmCategoryDTO = new FilmCategoryDTO();

        filmCategoryDTO.setFilm(filmDTO);
        filmCategoryDTO.setCategory(categoryDTO);
        filmCategoryDTO.setLastUpdate(filmCategory.getLastUpdate());

        return filmCategoryDTO;
    }

    public static FilmCategory toEntity(FilmCategoryDTO filmCategoryDTO) {
        FilmCategory filmCategory = new FilmCategory();

        filmCategory.setLastUpdate(filmCategoryDTO.getLastUpdate());
        filmCategory.setFilm(FilmConverter.toEntity(filmCategoryDTO.getFilm()));
        filmCategory.setCategory(CategoryConverter.toEntity(filmCategoryDTO.getCategory()));

        return filmCategory;
    }
}
