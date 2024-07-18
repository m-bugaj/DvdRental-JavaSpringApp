package com.example.dvdRental.api.mapper;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.CustomerInfoDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerInfoDTO customerToCustomerInfoDTO(Customer customer);

    Customer customerInfoDTOToCustomer(CustomerInfoDTO customerDTO);

//    CustomerDTO customerToCustomerDTO(Customer customer);

//    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    PostCustomerDTO toPostCustomerDTO(Customer customer);

    Customer toCustomer(PostCustomerDTO postCustomerDTO);
}
