package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.FilmCategoryDTO;
import com.example.dvdRental.model.key.FilmCategoryKey;
import com.example.dvdRental.services.FilmCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filmCategory")
public class FilmCategoryApiController {
    private final FilmCategoryService filmCategoryService;

    public FilmCategoryApiController(FilmCategoryService filmCategoryService) {
        this.filmCategoryService = filmCategoryService;
    }

    @GetMapping
    public List<FilmCategoryDTO> findAllFilmCategoryes() {
        return filmCategoryService.findAllFilmCategories();
    }

    @GetMapping("/{id}")
    public Optional<FilmCategoryDTO> findFilmCategoryById(@PathVariable("id") FilmCategoryKey filmCategoryId) {
        return filmCategoryService.findFilmCategoryById(filmCategoryId);
    }

    @PostMapping
    public ResponseEntity<FilmCategoryDTO> createNewFilmCategory(@RequestBody FilmCategoryDTO filmCategoryDTO) {
        return new ResponseEntity<FilmCategoryDTO>(filmCategoryService.createNewFilmCategory(filmCategoryDTO), HttpStatus.CREATED);
    }
}
