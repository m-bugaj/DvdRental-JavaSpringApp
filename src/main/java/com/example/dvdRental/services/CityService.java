package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.post.PostCityDTO;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<CityDTO> findAllCities();

    Optional<CityDTO> findCityById(Integer cityId);

    CityDTO createNewCity(PostCityDTO postCityDTO);

    CityDTO updateCity(Integer cityId, CityDTO cityDTO);

    void deleteCity(Integer cityId);
}
