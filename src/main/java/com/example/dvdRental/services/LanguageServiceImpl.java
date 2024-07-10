package com.example.dvdRental.services;

import com.example.dvdRental.api.model.LanguageDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.LanguageConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Language;
import com.example.dvdRental.model.Language;
import com.example.dvdRental.repositories.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public List<LanguageDTO> findAllLanguages() {
        return languageRepository
                .findAll()
                .stream()
                .map(LanguageConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LanguageDTO> findLanguageById(Integer languageId) {
        Optional<Language> optionalLanguage = languageRepository.findById(languageId);

        if (optionalLanguage.isPresent()) return optionalLanguage.map(LanguageConverter::toDTO);

        return Optional.empty();    }

    @Override
    public LanguageDTO createNewLanguage(LanguageDTO languageDTO) {
        Language language = LanguageConverter.toEntity(languageDTO);
        Language savedLanguage = languageRepository.save(language);

        return LanguageConverter.toDTO(savedLanguage);
    }

    @Override
    public LanguageDTO updateLanguage(Integer languageId, LanguageDTO languageDTO) {
        return null;
    }

    @Override
    public void deleteLanguage(Integer languageId) {

    }
}
