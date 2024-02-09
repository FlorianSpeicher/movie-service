package com.example.microservices;

import com.example.microservices.movieservice.MovieController;
import com.example.microservices.movieservice.entity.Actor;
import com.example.microservices.movieservice.entity.Movie;
import com.example.microservices.movieservice.entity.Regisseur;
import com.example.microservices.movieservice.service.MovieService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.*;
import static org.junit.Assert.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;

//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DisplayName("Tests of MovieController:")
@SpringBootTest(classes = MicroservicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MovieControllerTest {

    @Autowired
    public MovieController movieController;

    @Test
    @DisplayName("Give out the full list:")
    void listTest(){

        String fullList = "[Movie{id=1, title='Star Wars: Krieg der Sterne', length=03:00:00, episodes=4," +
                " ageRestriction=12, regisseur=Regisseur{id=1, firstName='George', lastName='Lucas'}, " +
                "reviews=[Review{id=1, comment='Good movie!'}]. actors=[Actor{id=7, firstName='Alden', " +
                "lastName='Ehrenreich'}, Actor{id=8, firstName='Woody', lastName='Harrelson'}]}, Movie{id=2, " +
                "title='Star Wars: Das Imperium schlägt zurück', length=03:00:00, episodes=5, ageRestriction=12, " +
                "regisseur=Regisseur{id=2, firstName='Irvin', lastName='Kershner'}, reviews=[Review{id=2, " +
                "comment='Very cool scenes'}]. actors=[Actor{id=9, firstName='Diego', lastName='Luna'}, " +
                "Actor{id=10, firstName='Ben', lastName='Mendelsohn'}]}, Movie{id=3, title='Star Wars: Rückkehr " +
                "der Jedi-Ritter', length=03:00:00, episodes=6, ageRestriction=12, regisseur=Regisseur{id=3, " +
                "firstName='Richard', lastName='Marquand'}, reviews=[Review{id=3, comment='Well playing actors'}]. " +
                "actors=[Actor{id=11, firstName='Mark', lastName='Hamill'}, Actor{id=12, firstName='Harrison', " +
                "lastName='Ford'}]}, Movie{id=4, title='Star Wars: Die dunkle Bedrohung', length=03:00:00, episodes=1, " +
                "ageRestriction=12, regisseur=Regisseur{id=1, firstName='George', lastName='Lucas'}, " +
                "reviews=[Review{id=4, comment='Nice one!'}]. actors=[Actor{id=1, firstName='Ewan', " +
                "lastName='McGregor'}, Actor{id=2, firstName='Liam', lastName='Neeson'}]}, Movie{id=5, title='Star Wars: " +
                "Angriff der Klonkrieger', length=03:00:00, episodes=2, ageRestriction=12, " +
                "regisseur=Regisseur{id=1, firstName='George', lastName='Lucas'}, reviews=[Review{id=5, " +
                "comment='Cool and amazing'}]. actors=[Actor{id=3, firstName='Natalie', lastName='Portman'}, " +
                "Actor{id=4, firstName='Hayden', lastName='Christensen'}]}, Movie{id=6, title='Star Wars: Die Rache " +
                "der Sith', length=03:00:00, episodes=3, ageRestriction=12, regisseur=Regisseur{id=1, " +
                "firstName='George', lastName='Lucas'}, reviews=[Review{id=6, comment='Best of all'}]. " +
                "actors=[Actor{id=5, firstName='Jimmy', lastName='Smits'}, Actor{id=6, firstName='Anthony', " +
                "lastName='Daniels'}]}, Movie{id=7, title='Star Wars: Das Erwachen der Macht', length=03:00:00, " +
                "episodes=7, ageRestriction=12, regisseur=Regisseur{id=4, firstName='J.J.', lastName='Abrams'}, " +
                "reviews=[Review{id=7, comment='Not so good'}]. actors=[Actor{id=13, firstName='Carrie', " +
                "lastName='Fisher'}, Actor{id=14, firstName='Kenny', lastName='Baker'}]}, Movie{id=8, " +
                "title='Star Wars: Die letzten Jedi', length=03:00:00, episodes=8, ageRestriction=12, " +
                "regisseur=Regisseur{id=5, firstName='Rian', lastName='Johnson'}, reviews=[Review{id=8, " +
                "comment='Not like the others'}]. actors=[Actor{id=15, firstName='David', lastName='Prowse'}, " +
                "Actor{id=16, firstName='Frank', lastName='Oz'}]}, Movie{id=9, title='Star Wars: Der Aufstieg " +
                "Skywalkers', length=03:00:00, episodes=9, ageRestriction=16, regisseur=Regisseur{id=4, " +
                "firstName='J.J.', lastName='Abrams'}, reviews=[Review{id=9, comment='Good but the others were " +
                "better'}]. actors=[Actor{id=17, firstName='Daisy', lastName='Ridley'}, Actor{id=18, " +
                "firstName='Adam', lastName='Driver'}]}]";

        Response response = get("/movies/list");

        Assertions.assertEquals(fullList, response.asString());




    }


    @Test
    @DisplayName("List a Movie by its id:")
    public void listByIdTest(){
        //Movie movie = new Movie("Star Wars: Krieg der Sterne", "03:00:00", 4, 12);
        String movie = "[Movie{id=1, title='Star Wars: Krieg der Sterne', length=03:00:00, episodes=4," +
            " ageRestriction=12, regisseur=Regisseur{id=1, firstName='George', lastName='Lucas'}, " +
                    "reviews=[Review{id=1, comment='Good movie!'}]. actors=[Actor{id=7, firstName='Alden', " +
                    "lastName='Ehrenreich'}, Actor{id=8, firstName='Woody', lastName='Harrelson'}]}]";

        Response response = get("/movies/list/1");
        System.out.println(response.statusCode());
        Assertions.assertEquals(movie, response.getBody().asString());

        Response response1 = get("/movies/list/{id}", 999);
        Assertions.assertEquals("", response1.asString());


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
        /*
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


         */

        Response response = RestAssured.post("https://localhost:8080/movies/list/regisseur");
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.asString());

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
