package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.api.model.post.PostRentalDTO;
import com.example.dvdRental.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rental")
public class RentalApiController {
    private final RentalService rentalService;

    public RentalApiController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalDTO> findAllRentales() {
        return rentalService.findAllRentals();
    }

    @GetMapping("/{id}")
    public Optional<RentalDTO> findRentalById(@PathVariable("id") Integer rentalId) {
        return rentalService.findRentalById(rentalId);
    }

    @PostMapping("/create")
    public ResponseEntity<RentalDTO> createNewRental(@RequestBody PostRentalDTO postRentalDTO) {
        return new ResponseEntity<RentalDTO>(rentalService.createNewRental(postRentalDTO), HttpStatus.CREATED);
    }
}
