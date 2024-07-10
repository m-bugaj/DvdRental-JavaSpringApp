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
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_generator")
    @SequenceGenerator(
            name = "language_generator",
            sequenceName = "language_language_id_seq",
            allocationSize = 1
    )
    @Column(name =  "language_id")
    private Integer languageId;
    private String name;

    @Column(name =  "last_update")
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "language")
    @Builder.Default
    private List<Film> films = new ArrayList<>();
}
