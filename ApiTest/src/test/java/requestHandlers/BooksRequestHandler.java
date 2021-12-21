package requestHandlers;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import routes.Routes;

public class BooksRequestHandler {

    public static Response getAllBooks() {
        Response response = RequestSpecificationManager.create()
                .get(Routes.books());

        return response;
    }

    public static Response reserveBooksAuthorized(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestSpecificationManager.createAuthorized()
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public static Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestSpecificationManager.create()
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public static Response getUserAuthorized(String userId) {
        Response response = RequestSpecificationManager.createAuthorized()
                .get(Routes.userAccount(userId));

        return response;
    }

    public static Response getUser(String userId) {
        Response response = RequestSpecificationManager.create()
                .get(Routes.userAccount(userId));

        return response;
    }

    public static Response returnBookAuthorized(ReturnBookRequest returnBookRequest) {
        Response response = RequestSpecificationManager.createAuthorized()
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public static Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = RequestSpecificationManager.create()
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public static Response returnAllBooksAuthorized(String userId) {
        Response response = RequestSpecificationManager.createAuthorized()
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }

    public static Response returnAllBooks(String userId) {
        Response response = RequestSpecificationManager.create()
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
