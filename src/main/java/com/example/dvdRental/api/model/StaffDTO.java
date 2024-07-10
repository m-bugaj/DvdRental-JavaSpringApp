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
public class StaffDTO {
    private Integer staffId;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private String email;
    private StoreDTO store;
    private Boolean active;
    private String username;
    private String password;
    private Timestamp lastUpdate;
    private byte[] picture;
}
