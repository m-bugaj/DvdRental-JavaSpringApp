package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class InventoryDTO {
    private Integer inventoryId;
    private FilmDTO film;
    private Integer storeId;
    private Timestamp lastUpdate;
}
