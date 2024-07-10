package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<CountryDTO> findAllCountries();

    Optional<CountryDTO> findCountryById(Integer countryId);

    CountryDTO createNewCountry(CountryDTO countryDTO);

    CountryDTO updateCountry(Integer countryId, CountryDTO countryDTO);

    void deleteCountry(Integer countryId);
}
