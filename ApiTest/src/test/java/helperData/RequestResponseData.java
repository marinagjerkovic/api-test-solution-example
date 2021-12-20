package helperData;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import model.entities.Book;

@Getter @Setter
public class RequestResponseData {
    public static Response response;
    public static RequestSpecification requestSpecification;

    public static Book reservedBook;
}
