package requestHandlers;

import helperData.EnvironmentData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.entities.Token;

public class RequestSpecificationManager {
    public static RequestSpecification create() {
        return RestAssured.given().baseUri(EnvironmentData.baseUri).contentType(ContentType.JSON);
    }

    public static RequestSpecification createAuthorized(Token token) {
        return RestAssured.given().baseUri(EnvironmentData.baseUri).contentType(ContentType.JSON).headers("Authorization", "Bearer " + token.getToken());
    }
}
