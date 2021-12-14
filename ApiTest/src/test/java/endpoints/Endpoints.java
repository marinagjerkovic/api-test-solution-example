package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.AuthorizationRequest;
import model.requests.Isbn;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import model.responses.*;

public class Endpoints {
    RequestSpecification requestSpecification;

    public Endpoints(String baseUri) {
        requestSpecification = RestAssured.given().baseUri(baseUri).contentType(ContentType.JSON);
    }

    public Response authenticateUser(AuthorizationRequest authorizationRequest) {
        Response response = requestSpecification
                .body(authorizationRequest)
                .post(Routes.generateToken());

        Token tokenResponse = response.getBody().as(Token.class);
        requestSpecification.headers("Authorization", "Bearer " + tokenResponse.token);
        return response;
    }

    public Response getAllBooks() {
        Response response = requestSpecification
                .get(Routes.books());

        return response;
    }

    public Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = requestSpecification
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public Response getUsersBooks(String userId) {
        Response response = requestSpecification
                .get(Routes.userAccount(userId));

        return response;
    }

    public Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = requestSpecification
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }
}
