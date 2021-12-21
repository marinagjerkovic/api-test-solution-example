package requestHandlers;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import helperData.TestContext;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import routes.Routes;

public class AccountRequestHandler {
    RequestResponseData requestResponseData;

    public AccountRequestHandler(RequestResponseData requestResponseData) {
        this.requestResponseData = requestResponseData;
    }

    public Response generateToken(AuthorizationRequest authorizationRequest) {
        Response response = RequestSpecificationManager.create()
                .body(authorizationRequest)
                .post(Routes.generateToken());

        return response;
    }

    public Response generateTokenWithoutBody() {
        Response response = RequestSpecificationManager.create()
                .post(Routes.generateToken());

        return response;
    }

}
