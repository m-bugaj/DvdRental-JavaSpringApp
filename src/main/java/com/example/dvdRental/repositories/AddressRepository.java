package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
