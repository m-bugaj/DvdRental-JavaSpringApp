package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmActorDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.FilmActorConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.FilmActor;
import com.example.dvdRental.model.key.FilmActorKey;
import com.example.dvdRental.repositories.FilmActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmActorServiceImpl implements FilmActorService {
    private final FilmActorRepository filmActorRepository;

    public FilmActorServiceImpl(FilmActorRepository filmActorRepository) {
        this.filmActorRepository = filmActorRepository;
    }


    @Override
    public List<FilmActorDTO> findAllFilmActors() {
        return filmActorRepository
                .findAll()
                .stream()
                .map(FilmActorConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmActorDTO> findFilmActorById(FilmActorKey filmActorId) {
        Optional<FilmActor> optionalFilmActor = filmActorRepository.findById(filmActorId);

        if (optionalFilmActor.isPresent()) return optionalFilmActor.map(FilmActorConverter::toDTO);

        return Optional.empty();    }

    @Override
    public FilmActorDTO createNewFilmActor(FilmActorDTO filmActorDTO) {
        FilmActor filmActor = FilmActorConverter.toEntity(filmActorDTO);
        FilmActor savedFilmActor = filmActorRepository.save(filmActor);

        return FilmActorConverter.toDTO(savedFilmActor);
    }

    @Override
    public FilmActorDTO updateFilmActor(Integer filmActorId, FilmActorDTO filmActorDTO) {
        return null;
    }

    @Override
    public void deleteFilmActor(Integer filmActorId) {

    }
}
