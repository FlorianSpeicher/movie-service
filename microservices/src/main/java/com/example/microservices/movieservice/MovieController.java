package com.example.microservices.movieservice;

import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import com.example.microservices.movieservice.service.MovieService;
import com.jayway.jsonpath.JsonPath;
import org.springframework.web.bind.annotation.*;

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
    public java.lang.String listByRegisseur(@RequestBody Regisseur regisseur){
        if (movieService.findByRegisseur(regisseur) != null){
            return movieService.findByRegisseur(regisseur).toString();
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
    public java.lang.String listByActor(@RequestBody Actor actor){
        if (movieService.findByActor(actor) != null){
            return movieService.findByActor(actor).toString();
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
    public java.lang.String listByTitle(@RequestBody java.lang.String title){
        if (movieService.findByTitle(title) == null){
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
    public Movie addMovie(@RequestBody Movie movie){
        movie.setId(0);
        Movie tempMovie = movieService.save(movie);
        return tempMovie;
    }

    /**
     * PostMapping for Adding an actor to a movie
     * @param id    the movie id
     * @param actor the actor that should be added
     * @return a String with a success/failure message
     */
    @PostMapping("/addActor/{id}")
    public String addActorToMovie(@PathVariable int id, @RequestBody Actor actor){
        Movie tempMovie = movieService.findById(id);
        if(tempMovie == null){
            return noEntryResponse;
        }else{
            tempMovie.setActors(actor);
            return movieService.save(actor).toString();
        }
    }

    /**
     * PutMapping to update a Movie in the dataBase
     * @param movie the Movie with the id and updated data
     * @return the updated movie with the same id
     */
    @PutMapping("/update/{id}")
    public String updateMovie(@RequestBody Movie movie, @PathVariable int id){
         Movie tempMovie = movieService.findById(id);
         if(tempMovie == null){
             return noEntryResponse;
         } else {
             movieService.findById(id).setTitle(movie.getTitle());
             movieService.findById(id).setLength(movie.getLength());
             movieService.findById(id).setEpisodes(movie.getEpisodes());
             movieService.findById(id).setAgeRestriction(movie.getAgeRestriction());
             movieService.findById(id).setRegisseur(movie.getRegisseur());
             movieService.save(movieService.findById(id));
             return movieService.findById(id).toString();
         }

    }

    /**
     * DeleteMapping to delete a movie from the dataBase
     * @param id the id of the movie that should be removed
     * @return a String with the success/failure message
     */
    @DeleteMapping("/delete/{id}")
    public java.lang.String deleteMovie(@PathVariable int id) {
        Movie tempMovie = movieService.findById(id);
        if(tempMovie == null){
            return noEntryResponse;
        } else {
            movieService.deleteById(id);
            return "Deleted!";
        }
    }
}
