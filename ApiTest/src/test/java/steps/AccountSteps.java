package steps;

import helpers.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import static org.testng.Assert.*;

public class AccountSteps extends BaseSteps {

    public AccountSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("generateToken request has been sent")
    public void generateToken_request_has_been_sent() {
        String username = getEnvironmentProperties().getUsername();
        String password = getEnvironmentProperties().getPassword();
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, password);

        Response response = getEndpoints().authenticateUser(authorizationRequest);
        assertEquals(response.statusCode(), 200);
        getEnvironmentProperties().setResponse(response);
    }
}
