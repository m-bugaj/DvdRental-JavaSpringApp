package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomerByCustomerId(Integer customerId);

//    Optional<Customer> findCustomerByEmail(String email);

    List<Customer> findByActiveBool(Boolean activeBool);

//    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrAddressCityCityContainingIgnoreCaseOrAddressCityCountryCountryContainingIgnoreCase(String firstName, String lastName, String city, String country);

    @Query("SELECT c FROM Customer c WHERE " +
            "CASE " +
            "WHEN (cast(:searchTerm as string) is null AND :activeBool is null) THEN TRUE " +
            "WHEN (cast(:searchTerm as string) is null AND :activeBool is not null) THEN (c.activeBool = :activeBool) " +
            "WHEN (cast(:searchTerm as string) is not null AND :activeBool is null) THEN" +
            " (LOWER(c.firstName) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.address.city.city) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.address.city.country.country) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))) " +
            "ELSE (LOWER(c.firstName) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.address.city.city) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%'))" +
            " OR LOWER(c.address.city.country.country) LIKE LOWER(CONCAT('%', cast(:searchTerm as string), '%')))" +
            " AND (c.activeBool = :activeBool) " +
            "END")
    Page<Customer> findCustomersBySearchTermAndActiveBool(
            @Param("activeBool") Boolean activeBool,
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );

    Optional<Customer> findCustomerByEmail(String email);
}
