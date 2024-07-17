package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.CustomerInfoDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.exceptions.DisposableEmailException;
import com.example.dvdRental.exceptions.DuplicateDataException;
import com.example.dvdRental.exceptions.InvalidDataException;
import com.example.dvdRental.exceptions.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();

    Optional<CustomerDTO> findCustomerById(Integer customerId);

    List<CustomerInfoDTO> findAllCustomersInfo(Pageable pageable);

    CustomerInfoDTO findCustomerInfoById(Integer customerId) throws NotFoundException;

    List<CustomerInfoDTO> findAllCustomerByActiveBoolAndSearch(
            Boolean activeBool,
            String searchTerm,
            Pageable pageable
    );

    CustomerDTO createNewCustomer(PostCustomerDTO postCustomerDTO) throws InvalidDataException, DuplicateDataException, NotFoundException, DisposableEmailException;

    CustomerDTO updateCustomer(Integer customerId, PostCustomerDTO postCustomerDTO) throws NotFoundException, InvalidDataException;

    void deleteCustomer(Integer customerId) throws NotFoundException;
}
