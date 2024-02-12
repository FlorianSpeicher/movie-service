package com.example.microservices;

import com.example.microservices.movieservice.entity.Review;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;

@ActiveProfiles("test")
@DisplayName("MovieControllerTest:")
@SpringBootTest(classes = MicroservicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieControllerTest {

    private String noEntryResponse = "No Entry!";


    @Test
    @Order(1)
    @DisplayName("List All:")
    void listTest(){
        String url = "/movies/list";

        String fullList = "[{\"id\":1, \"title\":\"Star Wars: Krieg der Sterne\", \"length\":\"03:00:00\", \"episodes\":4, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":1, \"comment\":\"Good movie!\"}], \"actors\":[{\"id\":7, \"firstName\":\"Alden\", \"lastName\":\"Ehrenreich\"}, {\"id\":8, \"firstName\":\"Woody\", \"lastName\":\"Harrelson\"}]}, {\"id\":2, \"title\":\"Star Wars: Das Imperium schlägt zurück\", \"length\":\"03:00:00\", \"episodes\":5, \"ageRestriction\":12, \"regisseur\":{\"id\":2, \"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}, \"reviews\"[{\"id\":2, \"comment\":\"Very cool scenes\"}], \"actors\":[{\"id\":9, \"firstName\":\"Diego\", \"lastName\":\"Luna\"}, {\"id\":10, \"firstName\":\"Ben\", \"lastName\":\"Mendelsohn\"}]}, {\"id\":3, \"title\":\"Star Wars: Rückkehr der Jedi-Ritter\", \"length\":\"03:00:00\", \"episodes\":6, \"ageRestriction\":12, \"regisseur\":{\"id\":3, \"firstName\":\"Richard\", \"lastName\":\"Marquand\"}, \"reviews\"[{\"id\":3, \"comment\":\"Well playing actors\"}], \"actors\":[{\"id\":11, \"firstName\":\"Mark\", \"lastName\":\"Hamill\"}, {\"id\":12, \"firstName\":\"Harrison\", \"lastName\":\"Ford\"}]}, {\"id\":4, \"title\":\"Star Wars: Die dunkle Bedrohung\", \"length\":\"03:00:00\", \"episodes\":1, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":4, \"comment\":\"Nice one!\"}], \"actors\":[{\"id\":1, \"firstName\":\"Ewan\", \"lastName\":\"McGregor\"}, {\"id\":2, \"firstName\":\"Liam\", \"lastName\":\"Neeson\"}]}, {\"id\":5, \"title\":\"Star Wars: Angriff der Klonkrieger\", \"length\":\"03:00:00\", \"episodes\":2, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":5, \"comment\":\"Cool and amazing\"}], \"actors\":[{\"id\":3, \"firstName\":\"Natalie\", \"lastName\":\"Portman\"}, {\"id\":4, \"firstName\":\"Hayden\", \"lastName\":\"Christensen\"}]}, {\"id\":6, \"title\":\"Star Wars: Die Rache der Sith\", \"length\":\"03:00:00\", \"episodes\":3, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":6, \"comment\":\"Best of all\"}], \"actors\":[{\"id\":5, \"firstName\":\"Jimmy\", \"lastName\":\"Smits\"}, {\"id\":6, \"firstName\":\"Anthony\", \"lastName\":\"Daniels\"}]}, {\"id\":7, \"title\":\"Star Wars: Das Erwachen der Macht\", \"length\":\"03:00:00\", \"episodes\":7, \"ageRestriction\":12, \"regisseur\":{\"id\":4, \"firstName\":\"J.J.\", \"lastName\":\"Abrams\"}, \"reviews\"[{\"id\":7, \"comment\":\"Not so good\"}], \"actors\":[{\"id\":13, \"firstName\":\"Carrie\", \"lastName\":\"Fisher\"}, {\"id\":14, \"firstName\":\"Kenny\", \"lastName\":\"Baker\"}]}, {\"id\":8, \"title\":\"Star Wars: Die letzten Jedi\", \"length\":\"03:00:00\", \"episodes\":8, \"ageRestriction\":12, \"regisseur\":{\"id\":5, \"firstName\":\"Rian\", \"lastName\":\"Johnson\"}, \"reviews\"[{\"id\":8, \"comment\":\"Not like the others\"}], \"actors\":[{\"id\":15, \"firstName\":\"David\", \"lastName\":\"Prowse\"}, {\"id\":16, \"firstName\":\"Frank\", \"lastName\":\"Oz\"}]}, {\"id\":9, \"title\":\"Star Wars: Der Aufstieg Skywalkers\", \"length\":\"03:00:00\", \"episodes\":9, \"ageRestriction\":16, \"regisseur\":{\"id\":4, \"firstName\":\"J.J.\", \"lastName\":\"Abrams\"}, \"reviews\"[{\"id\":9, \"comment\":\"Good but the others were better\"}], \"actors\":[{\"id\":17, \"firstName\":\"Daisy\", \"lastName\":\"Ridley\"}, {\"id\":18, \"firstName\":\"Adam\", \"lastName\":\"Driver\"}]}]";
        Response response = get(url);

        Assertions.assertEquals(fullList, response.asString());
    }


    @Test
    @Order(2)
    @DisplayName("List Movie by ID:")
    public void listByIdTest(){
        String url = "/movies/list/id/{id}";
        String movie = "{\"id\":1, \"title\":\"Star Wars: Krieg der Sterne\", \"length\":\"03:00:00\", \"episodes\":4, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":1, \"comment\":\"Good movie!\"}], \"actors\":[{\"id\":7, \"firstName\":\"Alden\", \"lastName\":\"Ehrenreich\"}, {\"id\":8, \"firstName\":\"Woody\", \"lastName\":\"Harrelson\"}]}";

        Response response = given().get(url, 1);
        Assertions.assertEquals(movie, response.asString());

        Response response1 = get(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());


    }

    @Test
    @Order(6)
    @DisplayName("Add Movie")
    public void addMovieTest() {
        String url = "/movies/add";
        String movie = "{\"title\":\"Star Wars: Der 10. Teil\", \"length\":\"03:00:00\", \"episodes\":10, \"ageRestriction\":18, \"regisseur\":{\"id\":2, \"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}}";
        String newMovie = "{\"id\":10, \"title\":\"Star Wars: Der 10. Teil\", \"length\":\"03:00:00\", \"episodes\":10, \"ageRestriction\":18, \"regisseur\":{\"id\":6, \"firstName\":\"unknown\", \"lastName\":\"unknown\"}, \"reviews\"[], \"actors\":[]}";

        Response response = given().body(movie).post(url);
        Assertions.assertEquals(newMovie, response.asString());
    }



    @Test
    @Order(3)
    @DisplayName("List Regisseur:")
    public void listRegisseurTest(){
        String url = "/movies/list/regisseur";
        String movie = "[{\"id\":2, \"title\":\"Star Wars: Das Imperium schlägt zurück\", \"length\":\"03:00:00\", \"episodes\":5, \"ageRestriction\":12, \"regisseur\":{\"id\":2, \"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}, \"reviews\"[{\"id\":2, \"comment\":\"Very cool scenes\"}], \"actors\":[{\"id\":9, \"firstName\":\"Diego\", \"lastName\":\"Luna\"}, {\"id\":10, \"firstName\":\"Ben\", \"lastName\":\"Mendelsohn\"}]}]";
        String regisseur = "{\"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}";

        Response response = given().body(regisseur).post(url);
        Assertions.assertEquals(movie, response.asString());

        String noRegisseur = "{\"firstName\":\"Max\", \"lastName\":\"Mustermann\"}";

        Response response1 = given().body(noRegisseur).post(url);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }
    @Test
    @Order(4)
    @DisplayName("List Actor:")
    public void listActorTest(){
        String url = "/movies/list/actor";
        String actor ="{\"firstName\":\"Ewan\",\"lastName\":\"McGregor\"}";
        String movies ="[{\"id\":4, \"title\":\"Star Wars: Die dunkle Bedrohung\", \"length\":\"03:00:00\", \"episodes\":1, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":4, \"comment\":\"Nice one!\"}], \"actors\":[{\"id\":1, \"firstName\":\"Ewan\", \"lastName\":\"McGregor\"}, {\"id\":2, \"firstName\":\"Liam\", \"lastName\":\"Neeson\"}]}]";

        Response response = given().body(actor).post(url);
        Assertions.assertEquals(movies, response.asString());

        String noActor = "{\"firstName\":\"Max\", \"lastName\":\"Mustermann\"}";

        Response response1 = given().body(noActor).post(url);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }
    @Test
    @Order(5)
    @DisplayName("List Title:")
    public void listTitleTest(){
        String url = "/movies/list/title";
        String title ="Star Wars: Die letzten Jedi";
        String movies ="[{\"id\":8, \"title\":\"Star Wars: Die letzten Jedi\", \"length\":\"03:00:00\", \"episodes\":8, \"ageRestriction\":12, \"regisseur\":{\"id\":5, \"firstName\":\"Rian\", \"lastName\":\"Johnson\"}, \"reviews\"[{\"id\":8, \"comment\":\"Not like the others\"}], \"actors\":[{\"id\":15, \"firstName\":\"David\", \"lastName\":\"Prowse\"}, {\"id\":16, \"firstName\":\"Frank\", \"lastName\":\"Oz\"}]}]";

        Response response = given().body(title).get(url);
        Assertions.assertEquals(movies, response.asString());

        String noTitle = "This is not a title";

        Response response1 = given().body(noTitle).get(url);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }

    @Test
    @DisplayName("Add Actor:")
    public void addActorTest(){
        String url = "/movies/addActor/{id}";
        String actor = "{\"id\":0, \"firstName\":\"Max\", \"lastName\":\"Mustermann\"}";

        String movie = "{\"id\":2, \"title\":\"Star Wars: Das Imperium schlägt zurück\", \"length\":\"03:00:00\", \"episodes\":5, \"ageRestriction\":12, \"regisseur\":{\"id\":2, \"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}, \"reviews\"[{\"id\":2, \"comment\":\"Very cool scenes\"}], \"actors\":[{\"id\":9, \"firstName\":\"Diego\", \"lastName\":\"Luna\"}, {\"id\":10, \"firstName\":\"Ben\", \"lastName\":\"Mendelsohn\"}, {\"id\":19, \"firstName\":\"Max\", \"lastName\":\"Mustermann\"}]}";

        Response response = given().body(actor).post(url, 2);
        Assertions.assertEquals(movie, response.asString());

        Response response1 = given().body(actor).post(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());

    }
    @Test
    @Order(8)
    @DisplayName("Update Movie:")
    public void updateTest(){
        String url = "/movies/update/{id}";
        String movie = "{\"title\":\"Star Wars: Die letzten Jedi\", \"length\":\"03:00:00\", \"episodes\":8, \"ageRestriction\":12, \"regisseur\":{\"id\":5, \"firstName\":\"Rian\", \"lastName\":\"Johnson\"}, \"reviews\"[{\"id\":8, \"comment\":\"Not like the others\"}], \"actors\":[{\"id\":15, \"firstName\":\"David\", \"lastName\":\"Prowse\"}, {\"id\":16, \"firstName\":\"Frank\", \"lastName\":\"Oz\"}]}";
        String movieResult = "{\"id\":8, \"title\":\"Star Wars: Die letzten Jedi\", \"length\":\"03:00:00\", \"episodes\":8, \"ageRestriction\":12, \"regisseur\":{\"id\":5, \"firstName\":\"Rian\", \"lastName\":\"Johnson\"}, \"reviews\"[{\"id\":8, \"comment\":\"Not like the others\"}], \"actors\":[{\"id\":15, \"firstName\":\"David\", \"lastName\":\"Prowse\"}, {\"id\":16, \"firstName\":\"Frank\", \"lastName\":\"Oz\"}]}";
        Response response = given().body(movie).put(url, 8);
        Assertions.assertEquals(movieResult, response.asString());

        Response response1 = given().body(movie).put(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }
    @Test
    @Order(7)
    @DisplayName("Delete Movie:")
    public void deleteTest(){
        String url = "/movies/delete/{id}";
        Response response = given().delete(url, 10);
        Assertions.assertEquals("Deleted!", response.asString());

        Response response1 = given().delete(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }

    @Test
    @Order(10)
    @DisplayName("Delete Review:")
    public void deleteReview(){
        String url = "/movies/deleteReview/{id}";
        Response response = given().delete(url, 1);
        Assertions.assertEquals("Deleted!", response.asString());

        Response response1 = given().delete(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());
    }

    @Test
    @Order(9)
    @DisplayName("Add Review:")
    public void addReview(){
        String url = "/movies/addReview/{id}";
        String review = "{\"comment\":\"Testcomment\"}";
        String movie = "{\"id\":4, \"title\":\"Star Wars: Die dunkle Bedrohung\", \"length\":\"03:00:00\", \"episodes\":1, \"ageRestriction\":12, \"regisseur\":{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, \"reviews\"[{\"id\":4, \"comment\":\"Nice one!\"}, {\"id\":10, \"comment\":\"Testcomment\"}], \"actors\":[{\"id\":1, \"firstName\":\"Ewan\", \"lastName\":\"McGregor\"}, {\"id\":2, \"firstName\":\"Liam\", \"lastName\":\"Neeson\"}]}";
        Response response = given().body(review).post(url, 4);
        Assertions.assertEquals(movie, response.asString());

        Response response1 = given().body(review).post(url, 999);
        Assertions.assertEquals(noEntryResponse, response1.asString());

    }
}
