package steps;

import helperData.EnvironmentData;
import helperData.RequestResponseData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.requests.AuthorizationRequest;
import model.responses.Message;
import model.entities.Token;
import requestHandlers.AccountRequestHandler;

import static org.testng.Assert.*;

public class AccountSteps {

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

    @And("response contains message with code {string} and message {string}")
    public void response_contains_message_with_code_and_message(String code, String messageText) {
        Message message = RequestResponseData.response.getBody().as(Message.class);
        assertEquals(message.getCode(), code);
        assertEquals(message.getMessage(), messageText);
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

    @Given("generateToken request has been sent with valid credentials")
    public void generateToken_request_has_been_sent_with_valid_credentials() {
        String username = EnvironmentData.username;
        String password = EnvironmentData.password;
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, password);
        Response response = AccountRequestHandler.generateToken(authorizationRequest);
        RequestResponseData.response = response;
        Token token = response.getBody().as(Token.class);
        RequestResponseData.tokenOfLoggedInUser = token;
    }
}
