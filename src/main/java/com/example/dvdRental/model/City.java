package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
    @SequenceGenerator(
            name = "city_generator",
            sequenceName = "city_city_id_seq",
            allocationSize = 1
    )
    @Column(name =  "city_id")
    private Integer cityId;

    private String city;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            nullable = false
    )
    private Country country;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "city")
    @Builder.Default
    private List<Address> Addresses = new ArrayList<>();
}
