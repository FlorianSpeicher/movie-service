package com.example.microservices.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity class for the actor object
 */
@Entity
@Table(name="actor")
public class Actor {

    /**
     * auto generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * first name of the actor
     */
    @Column(name = "first_name")
    private java.lang.String firstName;


    /**
     * last name of the actor
     */
    @Column(name = "last_name")
    private java.lang.String lastName;

    /**
     * the connection to the movies of the actor with fetchType LAZY
     */

    @ManyToMany(fetch =FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "movie-actors",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnore
    private List<Movie> movies;

    /**
     * Method to add a Movie to the actor in the save way
     * @param tempMovie the Movie that should be added to the actor
     */
    public void add(Movie tempMovie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(tempMovie);
        tempMovie.setActors(this);
    }

    //Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    public List<Movie> getMovies() { return movies; }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    //public void setMovies(){this.movies = null;}

    /**
     * Constructor of the class
     * @param firstName String
     * @param lastName String
     * @param movies List of movies the actor played in
     */
    public Actor(java.lang.String firstName, java.lang.String lastName, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    /**
     * argument less constructor with the names set as "unknown" and no movies connected
     */
    public Actor(){
        this("unknown", "unknown", null);
    }

    public Actor(java.lang.String firstName, java.lang.String lastName){
        this(firstName, lastName, null);
    }

    /**
     * toString Method to print out an instance of the actor class
     * @return String
     */
    @Override
    public java.lang.String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"firstName\":\"" + firstName +
                "\", \"lastName\":\"" + lastName +
                "\"}";
    }
}
