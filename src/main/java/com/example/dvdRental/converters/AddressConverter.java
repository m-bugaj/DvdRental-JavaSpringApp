package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.City;

public class AddressConverter {
    public static AddressDTO toDTO (Address address) {
        CityDTO cityDTO = CityConverter.toDTO(address.getCity());
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setAddress(address.getAddress());
        addressDTO.setAddress2(address.getAddress2());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setCity(cityDTO);
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setPhone(address.getPhone());
        addressDTO.setLastUpdate(address.getLastUpdate());


        return addressDTO;
    }

    public static Address toEntity (AddressDTO addressDTO) {
        City city = CityConverter.toEntity(addressDTO.getCity());
        Address address = new Address();

        address.setAddressId(addressDTO.getAddressId());
        address.setAddress(addressDTO.getAddress());
        address.setAddress2(addressDTO.getAddress2());
        address.setDistrict(addressDTO.getDistrict());
        address.setCity(city);
        address.setPostalCode(addressDTO.getPostalCode());
        address.setPhone(addressDTO.getPhone());
        address.setLastUpdate(addressDTO.getLastUpdate());

        return address;
    }
}
