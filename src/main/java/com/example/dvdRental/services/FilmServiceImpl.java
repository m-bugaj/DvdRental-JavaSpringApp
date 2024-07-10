package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.FilmConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Film;
import com.example.dvdRental.model.Film;
import com.example.dvdRental.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public List<FilmDTO> findAllFilms() {
        return filmRepository
                .findAll()
                .stream()
                .map(FilmConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmDTO> findFilmById(Integer filmId) {
        Optional<Film> optionalFilm = filmRepository.findById(filmId);

        if (optionalFilm.isPresent()) return optionalFilm.map(FilmConverter::toDTO);

        return Optional.empty();    }

    @Override
    public FilmDTO createNewFilm(FilmDTO filmDTO) {
        Film film = FilmConverter.toEntity(filmDTO);
        Film savedFilm = filmRepository.save(film);

        return FilmConverter.toDTO(savedFilm);
    }

    @Override
    public FilmDTO updateFilm(Integer filmId, FilmDTO filmDTO) {
        return null;
    }

    @Override
    public void deleteFilm(Integer filmId) {

    }
}
