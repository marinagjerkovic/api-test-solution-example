package requestHandlers;

import io.restassured.specification.RequestSpecification;

public abstract class RequestHandler {
    RequestSpecification requestSpecification;

    public RequestHandler(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }
}
