package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.model.City;
import com.example.dvdRental.model.Country;

public class CityConverter {
    public static CityDTO toDTO(City city) {
        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityId(city.getCityId());
        cityDTO.setCity(city.getCity());
        CountryDTO countryDTO = CountryConverter.toDTO(city.getCountry());
        cityDTO.setCountry(countryDTO);
        cityDTO.setLastUpdate(city.getLastUpdate());

        return  cityDTO;
    }

    public static City toEntity(CityDTO cityDTO) {
        Country country = CountryConverter.toEntity(cityDTO.getCountry());
        City city = new City();

        city.setCityId(cityDTO.getCityId());
        city.setCity(cityDTO.getCity());
        city.setCountry(country);
        city.setLastUpdate(cityDTO.getLastUpdate());

        return city;
    }
}
