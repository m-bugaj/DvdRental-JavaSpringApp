package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
