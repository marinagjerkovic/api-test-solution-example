package model.requests;

public class AuthorizationRequest {
    public String userName;
    public String password;

    public AuthorizationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
