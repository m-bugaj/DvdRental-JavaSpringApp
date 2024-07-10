package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.ActorDTO;
import com.example.dvdRental.model.Actor;

public class ActorConverter {
    public static ActorDTO toDTO(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();

        actorDTO.setActorId(actor.getActorId());
        actorDTO.setFirstName(actor.getFirstName());
        actorDTO.setLastName(actor.getLastName());
        actorDTO.setLastUpdate(actor.getLastUpdate());

        return actorDTO;
    }

    public static Actor toEntity(ActorDTO actorDTO) {
        Actor actor = new Actor();

        actor.setActorId(actorDTO.getActorId());
        actor.setFirstName(actorDTO.getFirstName());
        actor.setLastName(actorDTO.getLastName());
        actor.setLastUpdate(actorDTO.getLastUpdate());

        return actor;
    }
}
