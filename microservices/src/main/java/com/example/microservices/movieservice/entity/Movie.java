package com.example.microservices.movieservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name="title")
    private java.lang.String title;

    @Column(name="length")
    private java.lang.String length;

    @Column(name="episodes")
    private int episodes;

    @Column(name="age_restriction")
    private int ageRestriction;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "regisseur_id")
    @JsonIgnore
    private Regisseur regisseur;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private List<Review> reviews;

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

    public Movie(String title, String length, int episodes, int ageRestriction, Regisseur regisseur, List<Review> reviews, List<Actor> actors) {
        this.title = title;
        this.length = length;
        this.episodes = episodes;
        this.ageRestriction = ageRestriction;
        this.regisseur = regisseur;
        this.reviews = reviews;
        this.actors = actors;
    }

    public Movie(){
        this("unknown title", "00:00:00", 0, 0, new Regisseur(), new ArrayList<>(), new ArrayList<>() );
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"title\":\"" + title +
                "\", \"length\":\"" + length +
                "\", \"episodes\":" + episodes +
                ", \"ageRestriction\":" + ageRestriction +
                ", \"regisseur\":" + regisseur +
                ", \"reviews\":" + reviews +
                ", \"actors\":" + actors +
                "}";
    }
}
