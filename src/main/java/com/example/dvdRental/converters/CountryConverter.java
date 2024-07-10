package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.model.City;
import com.example.dvdRental.model.Country;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.hibernate.annotations.Synchronize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryConverter {
    public static CountryDTO toDTO(Country country) {

        CountryDTO countryDTO = new CountryDTO();

        countryDTO.setCountryId(country.getCountryId());
        countryDTO.setCountry(country.getCountry());
        countryDTO.setLastUpdate(country.getLastUpdate());

        return countryDTO;
    }

    public static Country toEntity(CountryDTO countryDTO) {

        Country country = new Country();

        country.setCountryId(countryDTO.getCountryId());
        country.setCountry(country.getCountry());
        country.setLastUpdate(countryDTO.getLastUpdate());

        return country;
    }
}
