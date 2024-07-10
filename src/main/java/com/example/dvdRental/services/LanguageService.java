package com.example.dvdRental.services;

import com.example.dvdRental.api.model.LanguageDTO;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    List<LanguageDTO> findAllLanguages();

    Optional<LanguageDTO> findLanguageById(Integer languageId);

    LanguageDTO createNewLanguage(LanguageDTO languageDTO);

    LanguageDTO updateLanguage(Integer languageId, LanguageDTO languageDTO);

    void deleteLanguage(Integer languageId);
}
