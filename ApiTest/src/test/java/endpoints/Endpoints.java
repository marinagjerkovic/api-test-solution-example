package endpoints;

import io.restassured.specification.RequestSpecification;

public abstract class Endpoints {
    RequestSpecification requestSpecification;

    public Endpoints(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }
}
