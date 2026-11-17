package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ApiTestBase {
    @Value("${local.server.port}") int port;
    @Autowired LibraryService service;

    @BeforeEach
    void baseSetUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        service.reset(); // Har test se pehle data clear
    }

    static void addBook(String isbn, int copies) {
        given().contentType(JSON)
            .body(Map.of("title", "Clean Code", "isbn", isbn,
                         "author", "R. Martin", "totalCopies", copies))
        .when().post("/books")
        .then().statusCode(201);
    }
}