package requestHandlers;

import helperData.RequestResponseData;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import routes.Routes;

public class AccountRequestHandler {

    public static Response generateToken(AuthorizationRequest authorizationRequest) {
        Response response = RequestResponseData.requestSpecification
                .body(authorizationRequest)
                .post(Routes.generateToken());

        return response;
    }

    public static Response generateTokenWithoutBody() {
        Response response = RequestResponseData.requestSpecification
                .post(Routes.generateToken());

        return response;
    }

}
