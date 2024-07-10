package com.example.dvdRental.services;

import com.example.dvdRental.api.model.ActorDTO;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    List<ActorDTO> findAllActors();

    Optional<ActorDTO> findActorById(Integer actorId);

    ActorDTO createNewActor(ActorDTO actorDTO);

    ActorDTO updateActor(Integer actorId, ActorDTO actorDTO);

    void deleteActor(Integer actorId);
}
