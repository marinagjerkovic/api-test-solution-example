package requestHandlers;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.requests.AuthorizationRequest;
import routes.Routes;

public class AccountRequestHandler {

    public static Response generateToken(AuthorizationRequest authorizationRequest) {
        Response response = RequestSpecificationManager.create()
                .body(authorizationRequest)
                .post(Routes.generateToken());

        return response;
    }

    public static Response generateTokenWithoutBody() {
        Response response = RequestSpecificationManager.create()
                .post(Routes.generateToken());

        return response;
    }

}
