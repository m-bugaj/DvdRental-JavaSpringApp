package com.example.dvdRental.model.key;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmCategoryKey implements Serializable {
    public Integer filmId;
    public Integer categoryId;
}
