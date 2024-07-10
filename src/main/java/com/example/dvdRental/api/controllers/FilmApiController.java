package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/film")
public class FilmApiController {
    private final FilmService filmService;

    public FilmApiController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDTO> findAllFilmes() {
        return filmService.findAllFilms();
    }

    @GetMapping("/{id}")
    public Optional<FilmDTO> findFilmById(@PathVariable("id") Integer filmId) {
        return filmService.findFilmById(filmId);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createNewFilm(@RequestBody FilmDTO filmDTO) {
        return new ResponseEntity<FilmDTO>(filmService.createNewFilm(filmDTO), HttpStatus.CREATED);
    }
}
