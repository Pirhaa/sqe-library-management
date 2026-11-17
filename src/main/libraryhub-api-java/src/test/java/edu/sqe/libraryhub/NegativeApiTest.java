package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NegativeApiTest extends ApiTestBase {

    // N1: Unknown ISBN -> 404
    @Test
    void n1_unknownIsbnReturns404() {
        given()
        .when().get("/books/does-not-exist")
        .then()
            .statusCode(404)
            .body("error", containsString("Unknown ISBN"));
    }

    // N2: Borrow when availableCopies == 0 -> 400
    @Test
    void n2_borrowWhenNoCopiesReturns400() {
        addBook("555", 1);
        given().contentType(JSON).body(Map.of("isbn", "555"))
            .when().post("/members/1/borrow").then().statusCode(200);

        given().contentType(JSON).body(Map.of("isbn", "555"))
        .when().post("/members/2/borrow")
        .then()
            .statusCode(400)
            .body("error", containsString("No copies available"));
    }

    // N3: Missing Title -> 400
    @Test
    void n3_missingTitleReturns400() {
        given().contentType(JSON)
            .body(Map.of("isbn", "666", "author", "X", "totalCopies", 1))
        .when().post("/books")
        .then()
            .statusCode(400)
            .body("error", containsString("title"));
    }

    // N4: totalCopies = 0 (400), = 1 (201)
    @Test
    void n4_totalCopiesLowerBoundary() {
        given().contentType(JSON)
            .body(Map.of("title", "T", "isbn", "777", "author", "A", "totalCopies", 0))
        .when().post("/books")
        .then().statusCode(400);

        given().contentType(JSON)
            .body(Map.of("title", "T", "isbn", "777", "author", "A", "totalCopies", 1))
        .when().post("/books")
        .then().statusCode(201);
    }

    // N5: Duplicate ISBN -> 409
    @Test
    void n5_duplicateIsbnReturns409() {
        addBook("888", 1);
        
        given().contentType(JSON)
            .body(Map.of("title", "Another", "isbn", "888", "author", "A", "totalCopies", 1))
        .when().post("/books")
        .then()
            .statusCode(409)
            .body("error", containsString("ISBN already exists"));
    }

    // N6: 5 loans ok, 6th -> 400
    @Test
    void n6_sixthBorrowHitsLoanLimit() {
        for (int i = 1; i <= 6; i++) addBook("book-" + i, 1);
        for (int i = 1; i <= 5; i++) {
            given().contentType(JSON).body(Map.of("isbn", "book-" + i))
                .when().post("/members/100/borrow").then().statusCode(200);
        }
        given().contentType(JSON).body(Map.of("isbn", "book-6"))
        .when().post("/members/100/borrow")
        .then()
            .statusCode(400)
            .body("error", containsString("Loan limit"));
    }

    // N7: Return not borrowed book -> 400
    @Test
    void n7_returnNotBorrowedReturns400() {
        addBook("999", 1);
        
        given().contentType(JSON).body(Map.of("isbn", "999"))
        .when().post("/members/200/return")
        .then()
            .statusCode(400)
            .body("error", containsString("not on loan"));
    }

    // N9: Fine tiers boundary values
    @ParameterizedTest
    @CsvSource({"0,None", "1,Low", "7,Low", "8,Medium", "14,Medium",
                "15,High", "30,High", "31,Severe"})
    void n9_fineTierBoundaries(int days, String tier) {
        given().queryParam("daysOverdue", days)
        .when().get("/members/7/fines")
        .then().statusCode(200).body("tier", equalTo(tier));
    }

    // N9 (Negative case): -1 days -> 400
    @Test
    void n9_negativeDaysOverdueReturns400() {
        given().queryParam("daysOverdue", -1)
        .when().get("/members/7/fines")
        .then().statusCode(400);
    }
}