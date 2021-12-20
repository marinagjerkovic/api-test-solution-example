package requestHandlers;

import helperData.RequestResponseData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import routes.Routes;

public class BooksRequestHandler {

    public static Response getAllBooks() {
        Response response = RequestResponseData.requestSpecification
                .get(Routes.books());

        return response;
    }

    public static Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestResponseData.requestSpecification
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public static Response getUser(String userId) {
        Response response = RequestResponseData.requestSpecification
                .get(Routes.userAccount(userId));

        return response;
    }

    public static Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = RequestResponseData.requestSpecification
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public static Response returnAllBooks(String userId) {
        Response response = RequestResponseData.requestSpecification
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
