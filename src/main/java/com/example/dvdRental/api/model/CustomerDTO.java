package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class CustomerDTO {
    private Integer customerId;
    private StoreDTO store;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private Boolean activebool;
    private LocalDate createDate;
    private Timestamp lastUpdate;
    private Integer active;
}
