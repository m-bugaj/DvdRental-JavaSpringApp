package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.post.PostCityDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.CityConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.City;
import com.example.dvdRental.model.City;
import com.example.dvdRental.model.Country;
import com.example.dvdRental.repositories.CityRepository;
import com.example.dvdRental.repositories.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<CityDTO> findAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(CityConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CityDTO> findCityById(Integer cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);

        if (optionalCity.isPresent()) return optionalCity.map(CityConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public CityDTO createNewCity(PostCityDTO postCityDTO) {
        Integer countryId = postCityDTO.getCountryId();

        Optional<Country> countryOptional = countryRepository.findById(countryId);

        if (countryOptional.isEmpty()) {
            throw new EntityNotFoundException("Country not found: " + countryId);
        }

        Country country = countryOptional.get();
        City city = new City();

        city.setCity(postCityDTO.getCity());
        city.setCountry(country);
        cityRepository.save(city);

        return CityConverter.toDTO(city);
    }

    @Override
    public CityDTO updateCity(Integer cityId, CityDTO cityDTO) {
        return null;
    }

    @Override
    public void deleteCity(Integer cityId) {

    }
}
