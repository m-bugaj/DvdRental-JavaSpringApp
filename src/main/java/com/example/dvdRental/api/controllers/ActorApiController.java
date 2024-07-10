package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.ActorDTO;
import com.example.dvdRental.api.model.CountryDTO;
import com.example.dvdRental.services.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actor")
public class ActorApiController {
    private final ActorService actorService;

    public ActorApiController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorDTO> findAllActor() {
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    public Optional<ActorDTO> findActorById(@PathVariable("id") Integer actorId) {
        return actorService.findActorById(actorId);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createNewActor(@RequestBody ActorDTO actorDTO) {
        return new ResponseEntity<ActorDTO>(actorService.createNewActor(actorDTO), HttpStatus.CREATED);
    }
}
