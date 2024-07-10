package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
