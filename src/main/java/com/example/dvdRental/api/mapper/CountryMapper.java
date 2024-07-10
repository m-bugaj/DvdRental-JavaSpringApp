package com.example.dvdRental.api.mapper;

import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO countryToCountryDTO(Country country);

    Country countryDTOToCountry(CountryDTO countryDTO);
}
