package com.example.dvdRental.model;

import com.example.dvdRental.util.MpaaRating;
import com.example.dvdRental.util.RatingConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
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
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_generator")
    @SequenceGenerator(
            name = "film_generator",
            sequenceName = "film_film_id_seq",
            allocationSize = 1
    )
    @Column(name = "film_id")
    private Integer filmId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name =  "release_year")
    @Min(1901)
    @Max(2155)
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(
            name = "language_id",
            nullable = false
    )
    private Language language;

    @Builder.Default
    @Column(
            name = "rental_duration",
            nullable = false
    )
    private Integer rentalDuration = 3;

    @Builder.Default
    @Column(
            precision = 4,
            scale = 2,
            name = "rental_rate",
            nullable = false
    )
    private BigDecimal rentalRate = new BigDecimal("4.99");

    private Integer length;

    @Builder.Default
    @Column(
            precision = 5,
            scale = 2,
            name = "replacement_cost",
            nullable = false
    )
    private BigDecimal replacementCost = new BigDecimal("19.99");

    @Builder.Default
    @Convert(converter = RatingConverter.class)
    private MpaaRating rating = MpaaRating.G;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "special_features",
            columnDefinition = "TEXT"
    )
    private String specialFeatures;

    private String fulltext;

    @OneToMany(mappedBy = "film")
    private List<Inventory> inventories = new ArrayList<>();



//    public MpaaRating getRating() {
//        return MpaaRating.fromDatabaseValue(rating);
//    }
//
//    public void setRating(MpaaRating rating) {
//        this.rating = rating.getDatabaseValue();
//    }
}
