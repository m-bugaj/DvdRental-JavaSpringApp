package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query(
            "SELECT r FROM Rental r " +
                    "WHERE r.inventory in :inventories " +
                    "AND r.returnDate is not null"
    )
    List<Rental> findRentalsByInventoriesWhereReturnDateIsNotNull(
            @Param("inventories") List<Inventory> inventories
    );
}
