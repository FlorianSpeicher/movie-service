package com.example.microservices.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity class for the movie object
 */
@Entity
@Table(name="movie")
public class Movie {

    /**
     * auto generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    /**
     * title of the movie
     */
    @Column(name="title")
    private java.lang.String title;

    /**
     * the length of the movie as a Duration object
     */
    @Column(name="length")
    private java.lang.String length;

    /**
     * the number of episodes
     */
    @Column(name="episodes")
    private int episodes;

    /**
     * the age restriction of the movie, normally 0,6,12,16,18
     */
    @Column(name="age_restriction")
    private int ageRestriction;

    /**
     * the connection to the régisseur with a Many-to-One type
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "regisseur_id")
    @JsonIgnore
    private Regisseur regisseur;

    /**
     * the connection to the List of reviews with a One to Many type
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private List<Review> reviews;

    /**
     * the connection to the List of actors with a Many-to-Many type and the load type LAZY
     */

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie-actors",
                joinColumns = {@JoinColumn(name = "movie_id")},
                inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    @JsonIgnore
    private List<Actor> actors;

    public Movie(String title, String length, int episodes, int ageRestriction) {
        this.title = title;
        this.length = length;
        this.episodes = episodes;
        this.ageRestriction = ageRestriction;
    }

    //Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getLength() {
        return length;
    }

    public void setLength(java.lang.String length) {
        this.length = length;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Regisseur getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(Regisseur regisseur) {
        this.regisseur = regisseur;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Actor> getActors() {
        return this.actors;
    }

    public void setActors(Actor actors) {
        this.actors.add(actors);
    }
    public void setAllActors(List<Actor> actors){this.actors = actors;}


    /**
     * Constructor of the class
     * @param title String
     * @param length as a Duration object
     * @param episodes int
     * @param ageRestriction int, normally 0,6,12,16,18
     * @param regisseur Regisseur object
     * @param reviews List of Review objects
     * @param actors List of Actor objects
     */
    public Movie(String title, String length, int episodes, int ageRestriction, Regisseur regisseur, List<Review> reviews, List<Actor> actors) {
        this.title = title;
        this.length = length;
        this.episodes = episodes;
        this.ageRestriction = ageRestriction;
        this.regisseur = regisseur;
        this.reviews = reviews;
        this.actors = actors;
    }

    /**
     * argument less constructor with the default values of:
     * title "unknown title"; Duration of 0; episodes of 0; ageRestriction of 0; no régisseurs and
     * an empty list of reviews and actors
     */
    public Movie(){
        this("unknown title", "00:00:00", 0, 0, new Regisseur(), new ArrayList<>(), new ArrayList<>() );
    }

    /**
     * toString Method to print out an instance of the class
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"title\":\"" + title +
                "\", \"length\":\"" + length +
                "\", \"episodes\":" + episodes +
                ", \"ageRestriction\":" + ageRestriction +
                ", \"regisseur\":" + regisseur +
                ", \"reviews\"" + reviews +
                ", \"actors\":" + actors +
                "}";
    }
}
