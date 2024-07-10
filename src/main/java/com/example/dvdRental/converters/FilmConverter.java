package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.api.model.LanguageDTO;
import com.example.dvdRental.model.Film;
import com.example.dvdRental.model.Language;

public class FilmConverter {
    public static FilmDTO toDTO(Film film) {
        LanguageDTO languageDTO = LanguageConverter.toDTO(film.getLanguage());
        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setFilmId(film.getFilmId());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setDescription(film.getDescription());
        filmDTO.setReleaseYear(film.getReleaseYear());
        filmDTO.setLanguage(languageDTO);
        filmDTO.setRentalDuration(film.getRentalDuration());
        filmDTO.setRentalRate(film.getRentalRate());
        filmDTO.setLength(film.getLength());
        filmDTO.setReplacementCost(film.getReplacementCost());
        filmDTO.setRating(film.getRating());
        filmDTO.setLastUpdate(film.getLastUpdate());
        filmDTO.setSpecialFeatures(film.getSpecialFeatures());
        filmDTO.setFulltext(film.getFulltext());

        return filmDTO;
    }

    public static Film toEntity(FilmDTO filmDTO) {
        Language language = LanguageConverter.toEntity(filmDTO.getLanguage());
        Film film = new Film();

        film.setFilmId(filmDTO.getFilmId());
        film.setTitle(filmDTO.getTitle());
        film.setDescription(filmDTO.getDescription());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setLanguage(language);
        film.setRentalDuration(filmDTO.getRentalDuration());
        film.setRentalRate(filmDTO.getRentalRate());
        film.setLength(filmDTO.getLength());
        film.setReplacementCost(filmDTO.getReplacementCost());
        film.setRating(filmDTO.getRating());
        film.setLastUpdate(filmDTO.getLastUpdate());
        film.setSpecialFeatures(filmDTO.getSpecialFeatures());
        film.setFulltext(filmDTO.getFulltext());

        return film;
    }
}
