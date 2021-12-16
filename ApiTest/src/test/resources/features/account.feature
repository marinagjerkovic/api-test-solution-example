Feature: Logging in

  Scenario Outline: Response contains error message when username and/or password are not provided
    When generateToken request has been sent with username <username> and password <password>
    Then response contains status code 400
    And response contains error message with code "1200" and message "UserName and Password required."

    Examples:
      | username         | password   |
      | ""               | ""         |
      | "marinajerkovic" | ""         |
      | ""               | "Test@123" |

  Scenario: Response contains correct token when user with given credentials exists
    When generateToken request has been sent with username "marinajerkovic" and password "Test@123"
    Then response contains status code 200
    And response contains correct token

  Scenario: Response contains incorrect token when user with given credentials doesn't exist
    When generateToken request has been sent with username "marinajerkovic" and password "invalid_password"
    Then response contains status code 200
    And response contains incorrect token