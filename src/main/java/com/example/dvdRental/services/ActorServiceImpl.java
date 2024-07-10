package com.example.dvdRental.services;

import com.example.dvdRental.api.model.ActorDTO;
import com.example.dvdRental.converters.ActorConverter;
import com.example.dvdRental.model.Actor;
import com.example.dvdRental.repositories.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<ActorDTO> findAllActors() {
        return actorRepository
                .findAll()
                .stream()
                .map(ActorConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ActorDTO> findActorById(Integer actorId) {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);

        if (actorOptional.isPresent()) return actorOptional.map(ActorConverter::toDTO);
        return Optional.empty();
    }

    @Override
    public ActorDTO createNewActor(ActorDTO actorDTO) {
        Actor actor = ActorConverter.toEntity(actorDTO);
        Actor savedActor = actorRepository.save(actor);

        return ActorConverter.toDTO(savedActor);
    }

    @Override
    public ActorDTO updateActor(Integer actorId, ActorDTO actorDTO) {
        return null;
    }

    @Override
    public void deleteActor(Integer actorId) {

    }



}
