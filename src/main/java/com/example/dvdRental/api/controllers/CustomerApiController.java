package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.CityDTO;
import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.CustomerInfoDTO;
import com.example.dvdRental.api.model.post.PostCityDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.model.Customer;
import com.example.dvdRental.services.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> findAllCustomeres() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<CustomerDTO> findCustomerInfoById(@PathVariable("id") Integer customerId) {
        return customerService.findCustomerById(customerId);
    }

    @GetMapping("/info")
    public List<CustomerInfoDTO> findAllCustomeresInfo(
            @PageableDefault(
            size = 20,
            page = 1,
            direction = Sort.Direction.ASC,
            sort = {"firstName"}
    ) Pageable pageable) {
        return customerService.findAllCustomersInfo(pageable);
    }

//    @GetMapping("/info/{id}")
//    public Optional<CustomerInfoDTO> findCustomerById(@PathVariable("id") Integer customerId) {
//        return customerService.findCustomerById(customerId);
//    }


    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody PostCustomerDTO postCustomerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(postCustomerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Integer id,
            @RequestBody PostCustomerDTO postCustomerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.updateCustomer(id, postCustomerDTO), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerInfoDTO>> findAllCustomerByActiveBoolAndSearch(
            @RequestParam(value = "status", required = false, defaultValue = "") String status,
            @RequestParam(value = "search", required = false) String searchTerm,
            @PageableDefault(
                    size = 20,
                    page = 1,
                    direction = Sort.Direction.ASC,
                    sort = {"firstName"}
            ) Pageable pageable) {

        System.out.println("Search term: " + searchTerm);
        Boolean activeBool;

        if (status.equals("active")) {
            activeBool = true;
        } else if (status.equals("disabled")) {
            activeBool = false;
        } else {
            activeBool = null;
        }

        List<CustomerInfoDTO> customersInfoDTO = customerService
                .findAllCustomerByActiveBoolAndSearch(
                        activeBool,
                        searchTerm,
                        pageable
                );
        return ResponseEntity.ok(customersInfoDTO);


    }

}
