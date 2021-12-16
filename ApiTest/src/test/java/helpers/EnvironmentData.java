package helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import model.responses.Book;

@Getter @Setter
public class EnvironmentData {
    Response response;
    RequestSpecification requestSpecification;

    String userId;
    String baseUri;
    String username;
    String password;

    Book reservedBook;
}
