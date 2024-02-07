package com.example.microservices.movieservice;

import com.example.microservices.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * DataBase Connection with JpaRepository
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
