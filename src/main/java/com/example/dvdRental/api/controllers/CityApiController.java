package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.post.PostCityDTO;
import com.example.dvdRental.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/city")
public class CityApiController {
    private final CityService cityService;

    public CityApiController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityDTO> findAllCityes() {
        return cityService.findAllCities();
    }

    @GetMapping("/{id}")
    public Optional<CityDTO> findCityById(@PathVariable("id") Integer cityId) {
        return cityService.findCityById(cityId);
    }

    @PostMapping
    public ResponseEntity<CityDTO> createNewCity(@RequestBody PostCityDTO postCityDTO) {
        return new ResponseEntity<CityDTO>(cityService.createNewCity(postCityDTO), HttpStatus.CREATED);
    }
}
