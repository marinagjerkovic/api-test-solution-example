package requestHandlers;

import helperData.EnvironmentData;
import helperData.GlobalData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationManager {
    public static RequestSpecification create() {
        return RestAssured.given().baseUri(EnvironmentData.baseUri).contentType(ContentType.JSON);
    }

    public static RequestSpecification createAuthorized() {
        return RestAssured.given().baseUri(EnvironmentData.baseUri).contentType(ContentType.JSON).headers("Authorization", "Bearer " + GlobalData.tokenOfLoggedInUser.getToken());
    }
}
