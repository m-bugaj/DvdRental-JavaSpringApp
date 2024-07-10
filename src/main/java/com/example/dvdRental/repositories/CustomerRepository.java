package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByActiveBool(Boolean activeBool);

//    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressCityCityContainingIgnoreCaseOrAddressCityCountryCountryContainingIgnoreCase(String firstName, String lastName, String city, String country);

    @Query("SELECT c FROM Customer c" +
            " WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
            " OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
            " OR LOWER(c.address.city.city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
            " OR LOWER(c.address.city.country.country) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Customer> findCustomersBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);
}
