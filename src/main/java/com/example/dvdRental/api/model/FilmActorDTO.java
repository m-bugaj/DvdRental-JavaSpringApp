package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class FilmActorDTO {
    private ActorDTO actor;
    private FilmDTO film;
    private Timestamp lastUpdate;
}
