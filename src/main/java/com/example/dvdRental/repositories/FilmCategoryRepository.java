package com.example.dvdRental.repositories;

import com.example.dvdRental.model.FilmCategory;
import com.example.dvdRental.model.key.FilmCategoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Integer> {
    Optional<FilmCategory> findById(FilmCategoryKey filmCategoryId);
}
