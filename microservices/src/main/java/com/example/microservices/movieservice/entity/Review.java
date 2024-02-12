package com.example.microservices.movieservice.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public Review(){
        this("unknown");
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"comment\":\"" + comment +
                "\"}";
    }
}
