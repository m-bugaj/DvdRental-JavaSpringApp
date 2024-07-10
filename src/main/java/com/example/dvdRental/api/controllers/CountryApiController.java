package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryApiController {
    private final CountryService countryService;

    @Autowired
    public CountryApiController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<CountryDTO> findAllCountryes() {
        return countryService.findAllCountries();
    }

    @GetMapping("/{id}")
    public Optional<CountryDTO> findCountryById(@PathVariable("id") Integer countryId) {
        return countryService.findCountryById(countryId);
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createNewCountry(@RequestBody CountryDTO countryDTO) {
//        CountryDTO createdCountry =
//        return ResponseEntity.ok(countryService.createNewCountry(countryDTO));
        return new ResponseEntity<CountryDTO>(countryService.createNewCountry(countryDTO), HttpStatus.CREATED);
    }



}
