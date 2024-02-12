package com.example.microservices.movieservice.entity;

import jakarta.persistence.*;

/**
 * An entity class for the review object
 */
@Entity
@Table(name = "review")
public class Review {

    /**
     * the auto generated id of the review
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * the comment of the review
     */
    @Column(name = "comment")
    private String comment;

    //The Getter and Setters

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

    /**
     * Constructor of the class
     * @param comment the comment of the review
     */
    public Review(String comment) {
        this.comment = comment;
    }

    /**
     * arguments less constructor with the "unknown" comment
     */
    public Review(){
        this("unknown");
    }

    /**
     * toString to give back an instance of the Review class
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"comment\":\"" + comment +
                "\"}";
    }
}
