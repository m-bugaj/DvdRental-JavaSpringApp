package com.example.dvdRental.api.model;

import com.example.dvdRental.util.MpaaRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class FilmDTO {
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private LanguageDTO language;
    private Integer rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private MpaaRating rating;
    private Timestamp lastUpdate;
    private String specialFeatures;
    private String fulltext;
}
