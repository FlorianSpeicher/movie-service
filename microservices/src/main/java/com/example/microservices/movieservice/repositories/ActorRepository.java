package com.example.microservices.movieservice.repositories;
import com.example.microservices.movieservice.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
