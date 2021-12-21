package requestHandlers;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import helperData.TestContext;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import routes.Routes;

public class BooksRequestHandler {
    RequestResponseData requestResponseData;

    public BooksRequestHandler(RequestResponseData requestResponseData) {
        this.requestResponseData = requestResponseData;
    }

    public Response getAllBooks() {
        Response response = RequestSpecificationManager.create()
                .get(Routes.books());

        return response;
    }

    public Response reserveBooksAuthorized(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestSpecificationManager.createAuthorized(requestResponseData.getTokenOfLoggedInUser())
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestSpecificationManager.create()
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public Response getUserAuthorized(String userId) {
        Response response = RequestSpecificationManager.createAuthorized(requestResponseData.getTokenOfLoggedInUser())
                .get(Routes.userAccount(userId));

        return response;
    }

    public Response getUser(String userId) {
        Response response = RequestSpecificationManager.create()
                .get(Routes.userAccount(userId));

        return response;
    }

    public Response returnBookAuthorized(ReturnBookRequest returnBookRequest) {
        Response response = RequestSpecificationManager.createAuthorized(requestResponseData.getTokenOfLoggedInUser())
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = RequestSpecificationManager.create()
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public Response returnAllBooksAuthorized(String userId) {
        Response response = RequestSpecificationManager.createAuthorized(requestResponseData.getTokenOfLoggedInUser())
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }

    public Response returnAllBooks(String userId) {
        Response response = RequestSpecificationManager.create()
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
