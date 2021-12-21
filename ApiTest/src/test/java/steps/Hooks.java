package steps;

import helperData.EnvironmentData;
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
    }
}
