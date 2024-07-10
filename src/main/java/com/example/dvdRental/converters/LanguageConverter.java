package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.LanguageDTO;
import com.example.dvdRental.model.Language;

public class LanguageConverter {
    public static LanguageDTO toDTO(Language language) {
        LanguageDTO languageDTO = new LanguageDTO();

        languageDTO.setLanguageId(language.getLanguageId());
        languageDTO.setName(language.getName());
        languageDTO.setLastUpdate(language.getLastUpdate());

        return languageDTO;
    }

    public static Language toEntity(LanguageDTO languageDTO) {
        Language language = new Language();

        language.setLanguageId(languageDTO.getLanguageId());
        language.setName(languageDTO.getName());
        language.setLastUpdate(languageDTO.getLastUpdate());

        return language;
    }
}
