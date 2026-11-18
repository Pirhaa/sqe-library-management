package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.Test;

class SchemaApiTest extends ApiTestBase {

    @Test
    void listBooksMatchesSchema() {
        addBook("schema-1", 1);
        addBook("schema-2", 2);

        given()
        .when().get("/books")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/book-list.schema.json"));
    }

    @Test
    void listBooksRespondsQuickly() {
        addBook("timing-1", 1);
        given()
        .when().get("/books")
        .then()
            .statusCode(200)
            .time(lessThan(1000L)); 
    }
}