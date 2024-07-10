package com.example.dvdRental.services;

import com.example.dvdRental.api.model.RentalDTO;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDTO> findAllRentals();

    Optional<RentalDTO> findRentalById(Integer rentalId);

    RentalDTO createNewRental(RentalDTO rentalDTO);

    RentalDTO updateRental(Integer rentalId, RentalDTO rentalDTO);

    void deleteRental(Integer rentalId);
}
