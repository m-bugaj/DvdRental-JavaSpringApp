package com.example.dvdRental.services;

import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.api.model.post.PostRentalDTO;
import com.example.dvdRental.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDTO> findAllRentals();

    Optional<RentalDTO> findRentalById(Integer rentalId);

    RentalDTO createNewRental(PostRentalDTO postRentalDTO);

    RentalDTO updateRental(Integer rentalId, RentalDTO rentalDTO);

    void deleteRental(Integer rentalId);
}
