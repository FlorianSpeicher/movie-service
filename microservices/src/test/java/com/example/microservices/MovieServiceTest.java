package com.example.microservices;

import com.example.microservices.movieservice.MovieController;
import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import java.nio.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicroservicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MovieServiceTest {

    @Autowired
    public MovieController movieController;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void listTest(){
        when()
                .get("/movies/list")
                .then()
                .statusCode(200);
    }


    @Test
    public void listByIdTest(){
        Movie movie = new Movie("Star Wars: Krieg der Sterne", "03:00:00", 4, 12);
        Movie result =  get("/movies/list/{id}", 1)
                .as(Movie.class);

         /*
        when()
                .get("/movies/list/{id}", 999)
                .then()
                .statusCode(404);


          */

    }
    /*
    @Test
    public void addMovieTest() throws IOException, ClassNotFoundException {
        //InputStream input = new FileInputStream("C:\\Users\\fspeicher\\IdeaProjects\\Einarbeitung\\Watchlist-Projekt\\watchlist\\microservices\\src\\test\\java\\com\\example\\microservices\\addMovie.json");
        //Movie erg = objectMapper.readValue(input, Movie.class);
        //String in = Files.readString(Path.of("C:\\Users\\fspeicher\\IdeaProjects\\Einarbeitung\\Watchlist-Projekt\\watchlist\\microservices\\src\\test\\java\\com\\example\\microservices\\addMovie.json"));
        //ObjectInputStream ois = new ObjectInputStream(input);
        //ObjectWriter input2 = new ObjectMapper().writer().withDefaultPrettyPrinter();
            Path path = Path.of("src/test/com/example/microservices/addMovie.json");
            String json = Files.readString(path);

       // String json = input2.writeValueAsString(ois.readObject());
        with().body(json)
                .when()
                .request("POST", "/movies/add")
                .then()
                .statusCode(201);
    }

     */

    @Test
    public void listRegisseurTest(){
        with().body(new Regisseur("George", "Lucas"))
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(200);
        with().body(new Regisseur("Testy", "Testmann"))
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(404);

    }
    @Test
    public void listActorTest(){
        with().body(new Actor("Ewan", "McGregor"))
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(200);
        with().body(new Actor("sd", "sd"))
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(404);
    }
    @Test
    public void listTitleTest(){
        with().body("Star Wars: Krieg der Sterne")
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(200);
        with().body("Star Wars: Der Krieg der Sterne")
                .when()
                .request("POST", "/movies/list/regisseur")
                .then()
                .statusCode(404);
    }

    @Test
    public void addActorTest(){
        with()
                .body(new Actor("Testy", "Testmann"))
                .when()
                .request("POST", "/movies/addActor/{id}", 10)
                .then()
                .statusCode(201);

    }
    @Test
    public void updateTest(){
        when()
                .request("PUT", "/movies/update/{id}", 10)
                .then()
                .statusCode(200);
    }
    @Test
    public void deleteTest(){
        when()
                .request("DELETE", "/movies/delete/{id}", 10)
                .then()
                .statusCode(200);

    }
}
