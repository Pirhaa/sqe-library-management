package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

class PositiveApiTest extends ApiTestBase {

    // 1. POST /books
    @Test
    void addBookReturnsCreated() {
        given().contentType(JSON)
            .body(Map.of("title", "Clean Code", "isbn", "111", "author", "R. Martin", "totalCopies", 2))
        .when().post("/books")
        .then()
            .statusCode(201)
            .contentType(JSON)
            .body("isbn", equalTo("111"))
            .body("title", equalTo("Clean Code"))
            .body("availableCopies", equalTo(2));
    }

    // 2. GET /books
    @Test
    void getAllBooksReturnsArray() {
        addBook("111", 1);
        addBook("222", 3);

        given()
        .when().get("/books")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("$", hasSize(2))
            .body("[0].isbn", equalTo("111"))
            .body("[1].isbn", equalTo("222"));
    }

    // 3. GET /books/{isbn}
    @Test
    void getBookByIsbnReturnsBook() {
        addBook("333", 5);

        given()
        .when().get("/books/333")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("333"))
            .body("availableCopies", equalTo(5));
    }

    // 4. POST /members/{id}/borrow
    @Test
    void borrowBookReducesCopies() {
        addBook("444", 1);

        given().contentType(JSON).body(Map.of("isbn", "444"))
        .when().post("/members/501/borrow")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("444"))
            .body("availableCopies", equalTo(0));
    }

    // 5. POST /members/{id}/return
    @Test
    void returnBookRestoresCopies() {
        addBook("555", 1);
        
        given().contentType(JSON).body(Map.of("isbn", "555"))
            .when().post("/members/502/borrow").then().statusCode(200);

        given().contentType(JSON).body(Map.of("isbn", "555"))
        .when().post("/members/502/return")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("555"))
            .body("availableCopies", equalTo(1));
    }

    // 6. GET /members/{id}/fines?daysOverdue=n
    @Test
    void finesReturnsCorrectTier() {
        given().queryParam("daysOverdue", 15)
        .when().get("/members/7/fines")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("memberId", equalTo(7))
            .body("daysOverdue", equalTo(15))
            .body("tier", equalTo("High"));
    }
}