package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
