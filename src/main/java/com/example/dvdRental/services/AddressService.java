package com.example.dvdRental.services;

import com.example.dvdRental.api.model.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressDTO> findAllAddresses();

    Optional<AddressDTO> findAddressById(Integer addressId);

    AddressDTO createNewAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Integer addressId, AddressDTO addressDTO);

    void deleteAddress(Integer addressId);
}
