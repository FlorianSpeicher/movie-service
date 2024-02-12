package com.example.microservices.movieservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity class for the régisseur object
 */
@Entity
@Table(name = "regisseur")
public class Regisseur {

    /**
     * auto generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * first name of the régisseur
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * last name of the régisseur
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * adding a list of movies the régisseur made
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "regisseur",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    //@JsonIgnore
    private List<Movie> movies;

    /**
     * A method to add Movies to an instance of the class in a save way
     * @param tempMovie the movie that should be added
     */
    public void add(Movie tempMovie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }

        movies.add(tempMovie);
        tempMovie.setRegisseur(this);
    }

    //Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Constructor of the class
     * @param firstName String
     * @param lastName String
     * @param movies a List of movies the régisseur made
     */
    public Regisseur(String firstName, String lastName, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    /**
     * argument less constructor with the names set to "unknown" and no movie added
     */
    public Regisseur(){
        this("unknown", "unknown", null);
    }
    public Regisseur(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * toString method to print out an instance of the class
     *
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"firstName\":\"" + firstName +
                "\", \"lastName\":\"" + lastName +
                "\"}";
    }
}
