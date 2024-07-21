package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
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

    @Query(
            "SELECT r FROM Rental r " +
                    "WHERE r.customer.active = 1 " +
                    "AND r.rentalDate is not null " +
                    "AND r.rentalDate < :conditionalDate"
    )
    List<Rental> findRentalsByActiveCustomersToInactive(
            @Param("conditionalDate") Timestamp conditionalDate
    );
}
