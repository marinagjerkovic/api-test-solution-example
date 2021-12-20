package requestHandlers;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import helperData.TestContext;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import routes.Routes;

public class AccountRequestHandler {
    EnvironmentData environmentData;
    RequestResponseData requestResponseData;

    public AccountRequestHandler(EnvironmentData environmentData, RequestResponseData requestResponseData) {
        this.environmentData = environmentData;
        this.requestResponseData = requestResponseData;
    }

    public Response generateToken(AuthorizationRequest authorizationRequest) {
        Response response = requestResponseData.getRequestSpecification()
                .body(authorizationRequest)
                .post(Routes.generateToken());

        return response;
    }

    public Response generateTokenWithoutBody() {
        Response response = requestResponseData.getRequestSpecification()
                .post(Routes.generateToken());

        return response;
    }

}
