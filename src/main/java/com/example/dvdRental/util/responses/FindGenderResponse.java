package com.example.dvdRental.util.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindGenderResponse {
    private String name;
    private String gender;
    private Float probability;
    private Integer count;
}
