package com.example.dvdRental.api.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CityInfoDTO {
    private String city;
    private CountryInfoDTO countryInfo;
}
