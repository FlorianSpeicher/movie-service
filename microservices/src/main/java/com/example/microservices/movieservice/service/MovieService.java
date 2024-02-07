package com.example.microservices.movieservice.service;

import com.example.microservices.movieservice.ActorRepository;
import com.example.microservices.movieservice.MovieRepository;
import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the MovieServiceInterface
 */
@Service
public class MovieService implements MovieServiceInterface {

    //Declaration of the repository
    private MovieRepository movieRepository;
    private ActorRepository actorRepository;

    /**
     * Constructor of the class
     * @param movieRepository is the accessing object of the database
     */
    @Autowired
    public MovieService (MovieRepository movieRepository, ActorRepository actorRepository){
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    /**
     * Find all Movies
     * @return the List of all Movies in the database
     */
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Find a Movie by a Movie id
     * @param id the id that should be found
     * @return the Movie with the id
     */
    @Override
    public Movie findById(int id) {
       Optional<Movie> movie = movieRepository.findById(id);
       Movie movie1 = null;
       if(movie.isPresent()){
           movie1 = movie.get();
           return movie1;
       } else {
           return null;
       }

    }

    /**
     * Saving a Movie into the database
     * @param movie the Movie that should be saved
     * @return the saved Movie with the generated id
     */
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Actor save(Actor actor) {
        if (actorRepository.findAll().contains(actor)){
            return actor;
        } else {
            return actorRepository.save(actor);
        }
    }

    /**
     * Deleting a Movie by id
     * @param id the Movie id
     */
    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }

    /**
     * Find a Movie by its title
     * @param title is a String with the exact title
     * @return a List of Movies that match with the title. List because episodes of series have the same title
     */
    @Override
    public List<Movie> findByTitle(java.lang.String title) {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> movieWithTitle = new ArrayList<>();
        for (Movie movie: movieList) {
            if (Objects.equals(title, movie.getTitle())){
                movieWithTitle.add(movie);
            }
        }
        return movieWithTitle;
    }

    /**
     * Find a Movie by its régisseur
     * @param regisseur the String with the name of the régisseur
     * @return a List of the Movie with that régisseur
     * The name in the request should be in the format "FirstLast" or "LastFirst"
     */
    @Override
    public List<Movie> findByRegisseur(Regisseur regisseur) {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> movieWithRegisseur = new ArrayList<>();
        for (Movie movie: movieList) {
            if (regisseur == movie.getRegisseur()){
                movieWithRegisseur.add(movie);
            }
        }
        return movieWithRegisseur;
    }


    /**
     * Find a Movie by its actor
     * @param actor the String with the name of the actor
     * @return a List of the Movies the actor played in
     */
    @Override
    public List<Movie> findByActor(Actor actor) {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> movieWithActor = new ArrayList<>();
        for (Movie movie: movieList) {
            if(movie.getActors().contains(actor)){
                movieWithActor.add(movie);
            }
        }
        return movieWithActor;
    }
}
