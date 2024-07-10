package com.example.dvdRental.services;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.converters.ActorConverter;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.model.Actor;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Country;
import com.example.dvdRental.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> findAllAddresses() {
        return addressRepository
                .findAll()
                .stream()
                .map(AddressConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddressDTO> findAddressById(Integer addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) return optionalAddress.map(AddressConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public AddressDTO createNewAddress(AddressDTO addressDTO) {
        Address address = AddressConverter.toEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);

        return AddressConverter.toDTO(savedAddress);
    }

    @Override
    public AddressDTO updateAddress(Integer addressId, AddressDTO addressDTO) {
        return null;
    }

    @Override
    public void deleteAddress(Integer addressId) {

    }
}
