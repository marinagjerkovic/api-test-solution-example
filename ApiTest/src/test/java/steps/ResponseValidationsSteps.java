package steps;

import helpers.TestContext;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class ResponseValidationsSteps extends BaseSteps {
    public ResponseValidationsSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("response contains status code {int}")
    public void response_contains_status_code(int statusCode) {
        int realStatusCode = getEnvironmentData().getResponse().getStatusCode();
        assertEquals(realStatusCode, statusCode);
    }
}
