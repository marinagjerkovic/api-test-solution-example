package steps;

import helperData.RequestResponseData;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class ResponseValidationsSteps {

    @Then("response contains status code {int}")
    public void response_contains_status_code(int statusCode) {
        int realStatusCode = RequestResponseData.response.getStatusCode();
        assertEquals(realStatusCode, statusCode);
    }
}
