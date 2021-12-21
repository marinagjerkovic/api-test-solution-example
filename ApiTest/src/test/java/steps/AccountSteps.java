package steps;

import helperData.RequestResponseData;
import helperData.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import model.responses.ErrorMessage;
import model.entities.Token;
import requestHandlers.AccountRequestHandler;

import static org.testng.Assert.*;

public class AccountSteps extends BaseSteps {

    public AccountSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("generateToken request has been sent")
    public void generateToken_request_has_been_sent_with_valid_credentials() {
        String username = getEnvironmentData().getUsername();
        String password = getEnvironmentData().getPassword();
        generateToken_request_has_been_sent_with_username_and_password(username, password);

        Response response = RequestResponseData.response;
        Token token = response.getBody().as(Token.class);
        RequestResponseData.requestSpecification.headers("Authorization", "Bearer " + token.getToken());
        RequestResponseData.token = token;
    }

    @When("generateToken request has been sent with username {string} and password {string}")
    public void generateToken_request_has_been_sent_with_username_and_password(String username, String password) {
        Response response;
        if (username.isEmpty() && password.isEmpty()) {
            response = AccountRequestHandler.generateTokenWithoutBody();
        } else {
            AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, password);
            response = AccountRequestHandler.generateToken(authorizationRequest);
        }
        RequestResponseData.response = response;
    }

    @And("response contains error message with code {string} and message {string}")
    public void response_contains_error_message_with_code_and_message(String code, String message) {
        ErrorMessage errorMessage = RequestResponseData.response.getBody().as(ErrorMessage.class);
        assertEquals(errorMessage.getCode(), code);
        assertEquals(errorMessage.getMessage(), message);
    }

    @And("response contains correct token")
    public void response_contains_correct_token() {
        Token token = RequestResponseData.response.as(Token.class);
        assertNotNull(token.getToken());
        assertNotNull(token.getExpires());
        assertEquals(token.getStatus(), "Success");
        assertEquals(token.getResult(), "User authorized successfully.");
    }

    @And("response contains incorrect token")
    public void response_contains_incorrect_token() {
        Token token = RequestResponseData.response.as(Token.class);
        assertNull(token.getToken());
        assertNull(token.getExpires());
        assertEquals(token.getStatus(), "Failed");
        assertEquals(token.getResult(), "User authorization failed.");
    }
}
