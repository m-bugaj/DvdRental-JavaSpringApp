package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findFirstByTitle(String filmTitle);
}
