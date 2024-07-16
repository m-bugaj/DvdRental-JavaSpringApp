package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.CustomerInfoDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.exceptions.ApiExceptionHandler;
import com.example.dvdRental.exceptions.NotFoundException;
import com.example.dvdRental.services.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Object> findCustomerInfoById(@PathVariable("id") Integer customerId) {
        try {
            CustomerInfoDTO customerInfoDTO = customerService.findCustomerInfoById(customerId);
            return ResponseEntity.ok(customerInfoDTO);
        } catch (NotFoundException nf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nf.toString());
        }
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
    public ResponseEntity<?> createNewCustomer(@RequestBody PostCustomerDTO postCustomerDTO) {
        try {
            return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(postCustomerDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return ApiExceptionHandler.apiHandleException(e);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable Integer id,
            @RequestBody PostCustomerDTO postCustomerDTO) {
        try {
            return new ResponseEntity<CustomerDTO>(customerService.updateCustomer(id, postCustomerDTO), HttpStatus.OK);
        } catch (Exception e) {
            return ApiExceptionHandler.apiHandleException(e);
        }

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

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return ApiExceptionHandler.apiHandleException(e);
        }

    }

}
