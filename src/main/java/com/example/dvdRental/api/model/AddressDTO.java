package com.example.dvdRental.api.model;

import lombok.*;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class AddressDTO {
    private Integer addressId;
    private String address;
    private String address2;
    private String district;
    private CityDTO city;
    private String postalCode;
    private String phone;
    private Timestamp lastUpdate;
}
