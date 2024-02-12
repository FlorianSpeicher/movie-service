package com.example.microservices.movieservice.repositories;
import com.example.microservices.movieservice.entity.Regisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisseurRepository extends JpaRepository<Regisseur, Integer> {
}
