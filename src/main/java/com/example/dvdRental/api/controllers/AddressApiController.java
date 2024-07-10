package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.ActorDTO;
import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressApiController {
    private final AddressService addressService;

    public AddressApiController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> findAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/{id}")
    public Optional<AddressDTO> findAddressById(@PathVariable("id") Integer addressId) {
        return addressService.findAddressById(addressId);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createNewAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<AddressDTO>(addressService.createNewAddress(addressDTO), HttpStatus.CREATED);
    }
}
