package com.example.microservices.movieservice.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regisseur")
public class Regisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "regisseur",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Movie> movies;

    public void add(Movie tempMovie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }

        movies.add(tempMovie);
        tempMovie.setRegisseur(this);
    }

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

    public Regisseur(String firstName, String lastName, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    public Regisseur(){
        this("unknown", "unknown", null);
    }
    public Regisseur(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"firstName\":\"" + firstName +
                "\", \"lastName\":\"" + lastName +
                "\"}";
    }
}
