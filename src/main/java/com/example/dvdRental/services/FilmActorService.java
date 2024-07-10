package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmActorDTO;
import com.example.dvdRental.model.key.FilmActorKey;

import java.util.List;
import java.util.Optional;

public interface FilmActorService {
    List<FilmActorDTO> findAllFilmActors();

    Optional<FilmActorDTO> findFilmActorById(FilmActorKey filmActorId);

    FilmActorDTO createNewFilmActor(FilmActorDTO filmActorDTO);

    FilmActorDTO updateFilmActor(Integer filmActorId, FilmActorDTO filmActorDTO);

    void deleteFilmActor(Integer filmActorId);
}
