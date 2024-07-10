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
public class RentalDTO {
    private Integer rentalId;
    private Timestamp rentalDate;
    private InventoryDTO inventory;
    private CustomerDTO customer;
    private Timestamp returnDate;
    private StaffDTO staff;
    private Timestamp lastUpdate;
}
