package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Film;
import com.example.dvdRental.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByFilmAndStoreId(Film film, Integer storeId);

    @Query(
            "SELECT i FROM Inventory i " +
            "WHERE LOWER(i.film.title) LIKE LOWER(CONCAT('%', cast(:filmTitle as string), '%')) " +
            "AND i.storeId = :storeId"
    )
    List<Inventory> findInventoriesByFilmTitleAndStoreId(
            @Param("filmTitle") String filmTitle,
            @Param("storeId") Integer storeId
    );
}
