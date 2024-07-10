package com.example.dvdRental.services;

import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.RentalConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    @Override
    public List<RentalDTO> findAllRentals() {
        return rentalRepository
                .findAll()
                .stream()
                .map(RentalConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RentalDTO> findRentalById(Integer rentalId) {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);

        if (optionalRental.isPresent()) return optionalRental.map(RentalConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public RentalDTO createNewRental(RentalDTO rentalDTO) {
        Rental rental = RentalConverter.toEntity(rentalDTO);
        Rental savedRental = rentalRepository.save(rental);

        return RentalConverter.toDTO(savedRental);
    }

    @Override
    public RentalDTO updateRental(Integer rentalId, RentalDTO rentalDTO) {
        return null;
    }

    @Override
    public void deleteRental(Integer rentalId) {

    }
}
