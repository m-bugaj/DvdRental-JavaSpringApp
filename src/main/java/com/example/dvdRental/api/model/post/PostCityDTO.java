package com.example.dvdRental.api.model.post;

import com.example.dvdRental.api.model.CountryDTO;
import lombok.*;

@Data
public class PostCityDTO {
    private String city;
    private Integer countryId;
}
