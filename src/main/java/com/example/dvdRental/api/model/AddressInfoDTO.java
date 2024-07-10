package com.example.dvdRental.api.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddressInfoDTO {
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private CityInfoDTO cityInfo;
}
