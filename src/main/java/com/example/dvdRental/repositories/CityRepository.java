package com.example.dvdRental.repositories;

import com.example.dvdRental.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
