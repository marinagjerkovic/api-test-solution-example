package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

public class WithoutCucumber {
    String userID = "2734a728-6f7a-4be3-849e-aaebbfff134c";
    String username = "marinajerkovic";
    String password = "Test@123";
    String baseUri = "https://bookstore.toolsqa.com";

    RequestSpecification requestSpecification = RestAssured.given().baseUri(baseUri).contentType(ContentType.JSON);

    @Test
    public void test() {

        // generate token
        Response response = requestSpecification.body("{\r\n  \"userName\": \"" + username + "\",\r\n  \"password\": \"" + password + "\"\r\n}")
                .post("/Account/v1/GenerateToken");

        assertTrue(response.asString().contains("token"));
        String token = response.jsonPath().getString("token");

        // get books
        response = requestSpecification.get("/BookStore/v1/Books");
        assertEquals(response.statusCode(), 200);
        List<Map<String, String>> allBooks = response.jsonPath().getList("books");
        assertTrue(allBooks.size() > 0);
        String bookIsbn = allBooks.get(0).get("isbn");

        // reserve book
        response = requestSpecification.headers("Authorization", "Bearer " + token)
                .body("{\r\n  \"userId\": \"" + userID + "\",\r\n  \"collectionOfIsbns\": [\r\n    {\r\n      \"isbn\": \"" + bookIsbn + "\"\r\n    }\r\n  ]\r\n}")
                .post("/BookStore/v1/Books");
        assertEquals(response.statusCode(), 201);

        // return book
        response = requestSpecification
                .body("{\r\n  \"userId\": \"" + userID + "\",\r\n  \"isbn\": \"" + bookIsbn + "\"\r\n}")
                .delete("/BookStore/v1/Book");
        assertEquals(response.statusCode(), 204);

        // get user (in order to see that user doesn't have any book reserved)
        response = requestSpecification
                .pathParam("userId", userID)
                .get("/Account/v1/User/{userId}");
        assertEquals(response.statusCode(), 200);
        List<String> usersBooks = response.jsonPath().getList("books");
        assertEquals(usersBooks.size(), 0);

    }
}
