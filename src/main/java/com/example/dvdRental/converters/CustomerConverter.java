package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Store;

public class CustomerConverter {
    public static CustomerDTO toDTO(Customer customer) {
        StoreDTO storeDTO = StoreConverter.toDTO(customer.getStore());
        AddressDTO addressDTO = AddressConverter.toDTO(customer.getAddress());
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setStore(storeDTO);
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(addressDTO);
        customerDTO.setActivebool(customer.getActiveBool());
        customerDTO.setCreateDate(customer.getCreateDate());
        customerDTO.setLastUpdate(customer.getLastUpdate());
        customerDTO.setActive(customer.getActive());
        customerDTO.setGender(customer.getGender());

        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        Store store = StoreConverter.toEntity(customerDTO.getStore());
        Address address = AddressConverter.toEntity(customerDTO.getAddress());
        Customer customer = new Customer();

        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setStore(store);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(address);
        customer.setActiveBool(customerDTO.getActivebool());
        customer.setCreateDate(customerDTO.getCreateDate());
        customer.setLastUpdate(customerDTO.getLastUpdate());
        customer.setActive(customerDTO.getActive());
        customer.setGender(customerDTO.getGender());

        return customer;
    }
}
