package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmCategoryDTO;
import com.example.dvdRental.model.key.FilmCategoryKey;

import java.util.List;
import java.util.Optional;

public interface FilmCategoryService {
    List<FilmCategoryDTO> findAllFilmCategories();

    Optional<FilmCategoryDTO> findFilmCategoryById(FilmCategoryKey filmCategoryId);

    FilmCategoryDTO createNewFilmCategory(FilmCategoryDTO filmCategoryDTO);

    FilmCategoryDTO updateFilmCategory(Integer filmCategoryId, FilmCategoryDTO filmCategoryDTO);

    void deleteFilmCategory(Integer filmCategoryId);
}
