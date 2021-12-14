package helpers;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import model.responses.Book;

@Getter @Setter
public class EnvironmentData {
    Response response;

    String userId;
    String baseUri;
    String username;
    String password;

    Book reservedBook;
}
