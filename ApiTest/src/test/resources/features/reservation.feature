Feature: Book reservation

  Background: User is logged in
    Given generateToken request has been sent with valid credentials
    Then response contains status code 200

  Scenario: Authorized user can reserve and return a book
    When returnAllBooks request has been sent
    Then response contains status code 204
    When getAllBooks request has been sent
    Then response contains status code 200
    And response contains not empty list of books
    When reserveBooks request has been sent
    Then response contains status code 201
    When getUser request has been sent
    Then response contains status code 200
    And response contains reserved book
    When returnBook request has been sent
    Then response contains status code 204
    When getUser request has been sent
    Then response contains status code 200
    And response doesn't contain reserved book