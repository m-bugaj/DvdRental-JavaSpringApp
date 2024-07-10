package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmDTO;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<FilmDTO> findAllFilms();

    Optional<FilmDTO> findFilmById(Integer filmId);

    FilmDTO createNewFilm(FilmDTO filmDTO);

    FilmDTO updateFilm(Integer filmId, FilmDTO filmDTO);

    void deleteFilm(Integer filmId);
}
