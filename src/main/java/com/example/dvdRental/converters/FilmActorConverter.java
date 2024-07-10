package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.ActorDTO;
import com.example.dvdRental.api.model.FilmActorDTO;
import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.model.Actor;
import com.example.dvdRental.model.Film;
import com.example.dvdRental.model.FilmActor;

public class FilmActorConverter {
    public static FilmActorDTO toDTO(FilmActor filmActor) {
        ActorDTO actorDTO = ActorConverter.toDTO(filmActor.getActor());
        FilmDTO filmDTO = FilmConverter.toDTO(filmActor.getFilm());
        FilmActorDTO filmActorDTO = new FilmActorDTO();

        filmActorDTO.setActor(actorDTO);
        filmActorDTO.setFilm(filmDTO);
        filmActorDTO.setLastUpdate(filmActor.getLastUpdate());

        return filmActorDTO;
    }

    public static FilmActor toEntity(FilmActorDTO filmActorDTO) {
        Actor actor = ActorConverter.toEntity(filmActorDTO.getActor());
        Film film = FilmConverter.toEntity(filmActorDTO.getFilm());
        FilmActor filmActor = new FilmActor();

        filmActor.setActor(actor);
        filmActor.setFilm(film);
        filmActor.setLastUpdate(filmActor.getLastUpdate());

        return filmActor;
    }
}
