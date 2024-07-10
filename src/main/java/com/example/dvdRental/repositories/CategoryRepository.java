package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
