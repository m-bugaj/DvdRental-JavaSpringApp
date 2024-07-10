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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(
            name = "category_generator",
            sequenceName = "category_category_id_seq",
            allocationSize = 1
    )
    @Column(name =  "category_id")
    private Integer categoryId;
    private String name;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
}
