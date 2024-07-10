package com.example.dvdRental.api.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class CountryDTO {
    private Integer countryId;
    private String country;
    private Timestamp lastUpdate;
}
