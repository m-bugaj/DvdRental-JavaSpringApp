package com.example.dvdRental.api.model;


import lombok.Data;

@Data
public class CustomerInfoDTO {
    private String firstName;
    private String lastName;
    private String email;
//    private String address;
//    private String address2;
//    private String district;
//    private String postalCode;
//    private String phone;
//    private String city;
//    private String country;
    private AddressInfoDTO addressInfoDTO;
}
