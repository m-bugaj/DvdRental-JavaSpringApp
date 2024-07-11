package com.example.dvdRental.services;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.CustomerInfoDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();

    Optional<CustomerDTO> findCustomerById(Integer customerId);

    List<CustomerInfoDTO> findAllCustomersInfo(Pageable pageable);

    Optional<CustomerInfoDTO> findCustomerInfoById();

    List<CustomerInfoDTO> findAllCustomerByActiveBoolAndSearch(Boolean activeBool, String searchTerm, Pageable pageable);

    CustomerDTO createNewCustomer(PostCustomerDTO postCustomerDTO);

    CustomerDTO updateCustomer(Integer customerId, PostCustomerDTO postCustomerDTO);

    void deleteCustomer(Integer customerId);
}
