package helperData;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import model.entities.Book;
import model.entities.Token;

@Getter @Setter
public class RequestResponseData {
    Response response;

    Book reservedBook;
    Token token;
}
