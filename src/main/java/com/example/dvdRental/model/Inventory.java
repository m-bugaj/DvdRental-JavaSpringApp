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
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_generator")
    @SequenceGenerator(
            name = "inventory_generator",
            sequenceName = "inventory_inventory_id_seq",
            allocationSize = 1
    )
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(
            name = "film_id",
            nullable = false
    )
    private Film film;

    @JoinColumn(
            name = "store_id",
            nullable = false
    )
    private Integer storeId;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals = new ArrayList<>();

}
