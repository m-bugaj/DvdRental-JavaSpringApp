package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByEmail(String email);
}
