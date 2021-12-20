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
        Response response = RequestSpecificationManager.create(environmentData.getBaseUri())
                .get(Routes.books());

        return response;
    }

    public Response reserveBooks(ReserveBooksRequest reserveBooksRequest) {
        Response response = RequestSpecificationManager.createAuthorized(environmentData.getBaseUri(), requestResponseData.getToken().getToken())
                .body(reserveBooksRequest)
                .post(Routes.books());

        return response;
    }

    public Response getUser(String userId) {
        Response response = RequestSpecificationManager.createAuthorized(environmentData.getBaseUri(), requestResponseData.getToken().getToken())
                .get(Routes.userAccount(userId));

        return response;
    }

    public Response returnBook(ReturnBookRequest returnBookRequest) {
        Response response = RequestSpecificationManager.createAuthorized(environmentData.getBaseUri(), requestResponseData.getToken().getToken())
                .body(returnBookRequest)
                .delete(Routes.book());

        return response;
    }

    public Response returnAllBooks(String userId) {
        Response response = RequestSpecificationManager.createAuthorized(environmentData.getBaseUri(), requestResponseData.getToken().getToken())
                .queryParam("UserId", userId)
                .delete(Routes.books());

        return response;
    }
}
