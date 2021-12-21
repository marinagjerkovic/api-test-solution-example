package steps;

import helperData.EnvironmentData;
import helperData.GlobalData;
import helperData.TestContext;
import io.cucumber.java.BeforeAll;
import io.restassured.response.Response;
import model.entities.Token;
import model.requests.AuthorizationRequest;
import requestHandlers.RequestSpecificationManager;
import routes.Routes;

import java.io.IOException;

public class Hooks extends BaseSteps {
    public Hooks(TestContext testContext) {
        super(testContext);
    }

    @BeforeAll
    public static void loadData_and_generateTokenWithValidCredentials() throws IOException {
        EnvironmentData.setValues();
        generateTokenWithValidCredentials();
    }

    public static void generateTokenWithValidCredentials() {
        String username = EnvironmentData.username;
        String password = EnvironmentData.password;
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, password);
        Response response = RequestSpecificationManager.create()
                .body(authorizationRequest)
                .post(Routes.generateToken());

        Token token = response.getBody().as(Token.class);
        GlobalData.tokenOfLoggedInUser = token;
    }
}
