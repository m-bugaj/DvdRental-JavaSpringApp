package com.example.dvdRental.api.model;

import lombok.*;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class CityDTO {
    private Integer cityId;
    private String city;
    private CountryDTO country;
    private Timestamp lastUpdate;
}
