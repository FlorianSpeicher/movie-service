package com.example.microservices.movieservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private java.lang.String firstName;

    @Column(name = "last_name")
    private java.lang.String lastName;

    @ManyToMany(fetch =FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "movie-actors",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnore
    private List<Movie> movies;

    public void add(Movie tempMovie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(tempMovie);
        tempMovie.setActors(this);
    }

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

    public Actor(java.lang.String firstName, java.lang.String lastName, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    public Actor(){
        this("unknown", "unknown", null);
    }

    public Actor(java.lang.String firstName, java.lang.String lastName){
        this(firstName, lastName, null);
    }

    @Override
    public java.lang.String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"firstName\":\"" + firstName +
                "\", \"lastName\":\"" + lastName +
                "\"}";
    }
}