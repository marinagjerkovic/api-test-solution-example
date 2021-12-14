package steps;

import helpers.ContextKey;
import helpers.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import static org.testng.Assert.*;

public class AccountSteps extends BaseSteps {

    public AccountSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() {
        String username = (String) getScenarioContext().get(ContextKey.USERNAME);
        String password = (String) getScenarioContext().get(ContextKey.PASSWORD);
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, password);

        Response response = getEndpoints().authenticateUser(authorizationRequest);
        assertEquals(response.statusCode(), 200);
        getScenarioContext().put(ContextKey.RESPONSE, response);
    }
}
