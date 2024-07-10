package com.example.dvdRental.model;

import com.example.dvdRental.model.key.FilmCategoryKey;
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
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryKey id;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
}
