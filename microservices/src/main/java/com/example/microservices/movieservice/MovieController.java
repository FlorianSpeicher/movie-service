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

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;
    private static final String noEntryResponse = "No Entry!";

    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping("/list")
    public java.lang.String listAll(){
        if(movieService.findAll() != null) {
            return movieService.findAll().toString();
        }else {
            return noEntryResponse;
        }
    }

    @GetMapping("/list/id/{id}")
    public String listById(@PathVariable int id){
        if(movieService.findById(id) != null) {
            return movieService.findById(id).toString();
        }else {
            return noEntryResponse;
        }
    }

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

    @GetMapping("/list/title")
    public String listByTitle(@RequestBody String title){
        if (!movieService.findByTitle(title).isEmpty()){
            return movieService.findByTitle(title).toString();
        }else{
            return noEntryResponse;
        }
    }

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

    @PutMapping("/update/{id}")
    public String updateMovie(@RequestBody String movie, @PathVariable int id){
        String[] movieSplit = movie.split(", \"reviews\"");
        String movieString = movieSplit[0];
        String reviewString = movieSplit[1];


        String[] movieStringSplit = movieString.split(", \"regisseur\":");
        movieString = movieStringSplit[0] + "}"; //movie rest
        String regisseurString = movieStringSplit[1]; //regisseur


        String[] secondSplit = reviewString.split(", \"actors\":");
        String actors = secondSplit[1]; //actors
        actors = actors.substring(0, actors.length()-1);
        String reviews = secondSplit[0]; //reviews

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
