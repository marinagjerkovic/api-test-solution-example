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
    EnvironmentData environmentData;
    RequestResponseData requestResponseData;

    public BooksRequestHandler(EnvironmentData environmentData, RequestResponseData requestResponseData) {
        this.environmentData = environmentData;
        this.requestResponseData = requestResponseData;
    }

    public Response getAllBooks() {
        Response response = requestResponseData.getRequestSpecification()
                .get(Routes.books());

        return response;
    }

    public Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = requestResponseData.getRequestSpecification()
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public Response getUser(String userId) {
        Response response = requestResponseData.getRequestSpecification()
                .get(Routes.userAccount(userId));

        return response;
    }

    public Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = requestResponseData.getRequestSpecification()
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public Response returnAllBooks(String userId) {
        Response response = requestResponseData.getRequestSpecification()
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
