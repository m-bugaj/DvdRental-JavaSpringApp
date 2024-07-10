package com.example.dvdRental.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class ActorDTO {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
}
