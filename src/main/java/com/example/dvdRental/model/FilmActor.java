package com.example.dvdRental.model;

import com.example.dvdRental.model.key.FilmActorKey;
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
public class FilmActor {
    @EmbeddedId
    private FilmActorKey id;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
}
