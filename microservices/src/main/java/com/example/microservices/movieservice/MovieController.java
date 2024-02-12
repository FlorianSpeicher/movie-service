package com.example.microservices.movieservice;

import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import com.example.microservices.movieservice.entity.Review;
import com.example.microservices.movieservice.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Controller of the movie service
 */
@RestController
@RequestMapping("/movies") //Main mapping begins with "/movies"
public class MovieController {

    //Service declaration
    private MovieService movieService;
    private static final String noEntryResponse = "No Entry!";

    /**
     * Constructor with the initialisation of the MovieService
     * @param movieService MovieService
     */
    public MovieController(MovieService movieService){this.movieService = movieService;}

    /**
     * GetMapping to list all the Movies
     * @return a String with all the Movies in it
     */
    @GetMapping("/list")
    public java.lang.String listAll(){
        if(movieService.findAll() != null) {
            return movieService.findAll().toString();
        }else {
            return noEntryResponse;
        }
    }

    /**
     * GetMapping to list a movie with a specific id
     * @param id the id of the searched movie
     * @return the Movie that has been found
     */
    @GetMapping("/list/id/{id}")
    public String listById(@PathVariable int id){
        if(movieService.findById(id) != null) {
            return movieService.findById(id).toString();
        }else {
            return noEntryResponse;
        }
    }

    /**
     * GetMapping of the list of movies with a specific régisseur
     * @param regisseur the name of the regisseur in the url
     * @return a List of Movies of the régisseur
     */
    @PostMapping("/list/regisseur")
    public String listByRegisseur(@RequestBody String regisseur){
        ObjectMapper objectMapper = new ObjectMapper();
        Regisseur newRegisseur;
        try {
            newRegisseur = objectMapper.readValue(regisseur, Regisseur.class);
        } catch (JsonProcessingException e) {
            return noEntryResponse;
        }
        if (!movieService.findByRegisseur(newRegisseur).isEmpty()){
            return movieService.findByRegisseur(newRegisseur).toString();
        }else{
            return noEntryResponse;
        }
    }

    /**
     * PostMapping of the list of movies with a specific actor
     * @param actor the actor object
     * @return a List of movies of the actor
     */
    @PostMapping("/list/actor")
    public String listByActor(@RequestBody String actor)  {
        ObjectMapper objectMapper = new ObjectMapper();
        Actor newActor;
        try {
            newActor = objectMapper.readValue(actor, Actor.class);
        } catch (JsonProcessingException e) {
            return noEntryResponse;
        }
        if (!movieService.findByActor(newActor).isEmpty()){
            return movieService.findByActor(newActor).toString();
        }else{
            return noEntryResponse;
        }
    }

    /**
     * GetMapping of the List of movies with a specific title
     * @param title the title in the url
     * @return a List of movies with this title
     */
    @GetMapping("/list/title")
    public String listByTitle(@RequestBody String title){
        if (!movieService.findByTitle(title).isEmpty()){
            return movieService.findByTitle(title).toString();
        }else{
            return noEntryResponse;
        }
    }

    /**
     * PostMapping for adding a new movie to the dataBase
     * @param movie the given Movie
     * @return the added movie with the id
     */
    @PostMapping("/add")
    public String addMovie(@RequestBody String movie){
        ObjectMapper objectMapper = new ObjectMapper();
        Movie finalMovie;
        try {
            finalMovie = objectMapper.readValue(movie, Movie.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        finalMovie.setId(0);
        return movieService.save(finalMovie).toString();
    }

    /**
     * PostMapping for Adding an actor to a movie
     * @param id    the movie id
     * @param actor the actor that should be added
     * @return a String with a success/failure message
     */
    @PostMapping("/addActor/{id}")
    public String addActorToMovie(@PathVariable int id, @RequestBody String actor){
        ObjectMapper objectMapper = new ObjectMapper();
        Actor finalActor;
        try {
            finalActor = objectMapper.readValue(actor, Actor.class);
        } catch (JsonProcessingException e) {
            System.out.println("Catch Fall");
            return noEntryResponse;
        }
        if (finalActor.getId() == 0){
            movieService.save(finalActor);
        }else{
            List<Actor> actors = movieService.findAllActors();
            for (Actor actor1: actors) {
                if (Objects.equals(actor1.getFirstName(), finalActor.getFirstName()) && Objects.equals(actor1.getLastName(), finalActor.getLastName())){
                    finalActor = actor1;
                }
            }
            if (actors.isEmpty()){
                return noEntryResponse;
            }
        }
        Movie tempMovie = movieService.findById(id);
        if(tempMovie == null){
            return noEntryResponse;
        }else{
            tempMovie.setActors(finalActor);
            movieService.save(finalActor);
            return movieService.findById(id).toString();
        }
    }

    /**
     * PutMapping to update a Movie in the dataBase
     * @param movie the Movie with the id and updated data
     * @return the updated movie with the same id
     */
    @PutMapping("/update/{id}")
    public String updateMovie(@RequestBody String movie, @PathVariable int id){
        String[] movieSplit = movie.split(", \"reviews\"");
        String movieString = movieSplit[0];
        String reviewString = movieSplit[1];


        String[] movieStringSplit = movieString.split(", \"regisseur\":");
        movieString = movieStringSplit[0] + "}";
        String regisseurString = movieStringSplit[1];


        String[] secondSplit = reviewString.split(", \"actors\":");
        String actors = secondSplit[1];
        actors = actors.substring(0, actors.length()-1);
        String reviews = secondSplit[0];

        ObjectMapper objectMapper = new ObjectMapper();
        Movie tempMovie;
        Review[] reviewList;
        Actor[] actorList;
        Regisseur regisseur;
        try {
            tempMovie = objectMapper.readValue(movieString, Movie.class);
            tempMovie.setId(id);
            reviewList = objectMapper.readValue(reviews, Review[].class);
            actorList = objectMapper.readValue(actors, Actor[].class);
            regisseur = objectMapper.readValue(regisseurString, Regisseur.class);


        } catch (JsonProcessingException e) {
            System.out.println("Catch Fall!");
            return noEntryResponse;
        }
        List<Review> finalReviews = new ArrayList<>(Arrays.asList(reviewList));
        tempMovie.setReviews(finalReviews);
        List<Actor> finalActors = new ArrayList<>(Arrays.asList(actorList));
        tempMovie.setAllActors(finalActors);
        tempMovie.setRegisseur(regisseur);

        if (movieService.findById(id) == null){
            return noEntryResponse;
        }else {
            return movieService.save(tempMovie).toString();
        }






    }

    /**
     * DeleteMapping to delete a movie from the dataBase
     * @param id the id of the movie that should be removed
     * @return a String with the success/failure message
     */
    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        Movie tempMovie = movieService.findById(id);
        if(tempMovie == null){
            return noEntryResponse;
        } else {
            movieService.deleteById(id);
            return "Deleted!";
        }
    }

    @PostMapping("/addReview/{id}")
    public String addReview(@PathVariable int id, @RequestBody String review){
        ObjectMapper objectMapper = new ObjectMapper();
        Review review1;
        try {
            review1 = objectMapper.readValue(review, Review.class);
        } catch (JsonProcessingException e) {
            return noEntryResponse;
        }
        Movie tempMovie = movieService.findById(id);
        if(tempMovie == null){
            return noEntryResponse;
        }else {
            List<Review> tempList = tempMovie.getReviews();
            tempList.add(review1);
           tempMovie.setReviews(tempList);
            return movieService.save(tempMovie).toString();
        }
    }

    @DeleteMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable int id){
       if (movieService.findReviewById(id) != null){
           movieService.deleteReview(id);
           return "Deleted!";
       }else {
           return noEntryResponse;
       }
    }
}
