package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.AuthorizationRequest;

public class AccountEndpoints extends Endpoints {
    public AccountEndpoints(RequestSpecification requestSpecification) {
        super(requestSpecification);
    }

    public Response generateToken(AuthorizationRequest authorizationRequest) {
        Response response = requestSpecification
                .body(authorizationRequest)
                .post(Routes.generateToken());

        return response;
    }

    public Response generateTokenWithoutBody() {
        Response response = requestSpecification
                .post(Routes.generateToken());

        return response;
    }

}
