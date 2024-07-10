package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CityListDTO {
    private List<CityDTO> cityDTOList;
}
