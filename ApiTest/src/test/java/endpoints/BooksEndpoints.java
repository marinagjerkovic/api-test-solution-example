package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;

public class BooksEndpoints extends Endpoints {
    public BooksEndpoints(RequestSpecification requestSpecification) {
        super(requestSpecification);
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

    public Response getUser(String userId) {
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

    public Response returnAllBooks(String userId) {
        Response response = requestSpecification
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
