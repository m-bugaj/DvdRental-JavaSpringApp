package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_generator")
    @SequenceGenerator(
            name = "actor_generator",
            sequenceName = "actor_actor_id_seq",
            allocationSize = 1
    )
    @Column(name =  "actor_id")
    private Integer actorId;

    @Column(name =  "first_name")
    private String firstName;

    @Column(name =  "last_name")
    private String lastName;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
}
