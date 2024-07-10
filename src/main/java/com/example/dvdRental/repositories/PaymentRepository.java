package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
