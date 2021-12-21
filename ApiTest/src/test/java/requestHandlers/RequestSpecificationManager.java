package requestHandlers;

import helperData.RequestResponseData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationManager {
    public static RequestSpecification create(String baseUri) {
        return RestAssured.given().baseUri(baseUri).contentType(ContentType.JSON);
    }

    public static RequestSpecification createAuthorized(String baseUri) {
        return RestAssured.given().baseUri(baseUri).contentType(ContentType.JSON).headers("Authorization", "Bearer " + RequestResponseData.token);
    }
}
