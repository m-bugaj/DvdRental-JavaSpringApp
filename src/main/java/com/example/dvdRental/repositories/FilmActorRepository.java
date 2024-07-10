package com.example.dvdRental.repositories;

import com.example.dvdRental.model.FilmActor;
import com.example.dvdRental.model.key.FilmActorKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {
    Optional<FilmActor> findById(FilmActorKey filmActorId);
}
