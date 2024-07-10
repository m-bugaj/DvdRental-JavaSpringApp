package com.example.dvdRental.services;

import com.example.dvdRental.api.mapper.CountryMapper;
import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.converters.CountryConverter;
import com.example.dvdRental.model.Country;
import com.example.dvdRental.model.Country;
import com.example.dvdRental.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    CountryRepository countryRepository;
    CountryMapper countryMapper;

//    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }


    @Override
    public List<CountryDTO> findAllCountries() {
        return countryRepository
                .findAll()
                .stream()
                .map(CountryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CountryDTO> findCountryById(Integer countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);

        if (optionalCountry.isPresent()) return optionalCountry.map(CountryConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public CountryDTO createNewCountry(CountryDTO countryDTO) {
//        Country country = countryRepository.findById(countryDTO.getCountryId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));

        Country country = countryMapper.countryDTOToCountry(countryDTO);
        Country savedCountry = countryRepository.save(country);

        return countryMapper.countryToCountryDTO(savedCountry);
    }

    @Override
    public CountryDTO updateCountry(Integer countryId, CountryDTO countryDTO) {
        return null;
    }

    @Override
    public void deleteCountry(Integer countryId) {

    }
}
