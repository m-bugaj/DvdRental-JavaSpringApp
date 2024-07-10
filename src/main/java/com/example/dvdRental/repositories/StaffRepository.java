package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
