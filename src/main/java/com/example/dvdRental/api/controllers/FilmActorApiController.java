package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.FilmActorDTO;
import com.example.dvdRental.model.key.FilmActorKey;
import com.example.dvdRental.services.FilmActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filmActor")
public class FilmActorApiController {
    private final FilmActorService filmActorService;

    public FilmActorApiController(FilmActorService filmActorService) {
        this.filmActorService = filmActorService;
    }

    @GetMapping
    public List<FilmActorDTO> findAllFilmActores() {
        return filmActorService.findAllFilmActors();
    }

    @GetMapping("/{id}")
    public Optional<FilmActorDTO> findFilmActorById(@PathVariable("id") FilmActorKey filmActorId) {
        return filmActorService.findFilmActorById(filmActorId);
    }

    @PostMapping
    public ResponseEntity<FilmActorDTO> createNewFilmActor(@RequestBody FilmActorDTO filmActorDTO) {
        return new ResponseEntity<FilmActorDTO>(filmActorService.createNewFilmActor(filmActorDTO), HttpStatus.CREATED);
    }
}
