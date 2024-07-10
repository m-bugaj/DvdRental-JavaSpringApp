package com.example.dvdRental.repositories;

import com.example.dvdRental.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
