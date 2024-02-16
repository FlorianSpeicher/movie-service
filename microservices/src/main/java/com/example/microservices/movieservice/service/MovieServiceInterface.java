package com.example.microservices.movieservice.service;

import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import com.example.microservices.movieservice.entity.Review;

import java.util.List;

/**
 * interface of Methods used for accessing the database
 */
public interface MovieServiceInterface{
    List<Movie> findAll();
    Movie findById(int id);
    Movie save(Movie movie);
    Actor save(Actor actor);
    void deleteById(int id);
    List<Movie> findByTitle(java.lang.String title);
    List<Movie> findByRegisseur(Regisseur regisseur);
    List<Movie> findByActor(Actor actor);
    List<Actor> findAllActors();
    List<Regisseur> findAllRegisseurs();
    Review save(Review review);
    void deleteReview(int id);
    Review findReviewById(int id);

    List<Movie> findAllMoviesOfRegisseur(int id);

    List<Movie> findAllMoviesOfActor(int id);
}

