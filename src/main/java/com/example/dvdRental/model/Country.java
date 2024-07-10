package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(
            name = "country_generator",
            sequenceName = "country_country_id_seq",
            allocationSize = 1
    )
    @Column(
            name =  "country_id"
    )
    private Integer countryId;

    @Column(nullable = false)
    private String country;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "country")
    @Builder.Default
    private List<City> cities = new ArrayList<>();

}
