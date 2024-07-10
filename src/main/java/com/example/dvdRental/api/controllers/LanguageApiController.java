package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.LanguageDTO;
import com.example.dvdRental.services.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/language")
public class LanguageApiController {
    private final LanguageService languageService;

    public LanguageApiController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<LanguageDTO> findAllLanguagees() {
        return languageService.findAllLanguages();
    }

    @GetMapping("/{id}")
    public Optional<LanguageDTO> findLanguageById(@PathVariable("id") Integer languageId) {
        return languageService.findLanguageById(languageId);
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> createNewLanguage(@RequestBody LanguageDTO languageDTO) {
        return new ResponseEntity<LanguageDTO>(languageService.createNewLanguage(languageDTO), HttpStatus.CREATED);
    }
}
