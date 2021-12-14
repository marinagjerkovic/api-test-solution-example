Feature: Book reservation

  Background: User is authorized
    Given generateToken request has been sent

  Scenario: Authorized user can reserve and return a book
    And returnAllBooks request has been sent
    When getAllBooks request has been sent
    Then response contains not empty list of books
    When reserveBooks request for first book has been sent
    And getUser request has been sent
    Then response contains reserved book
    When returnBook request has been sent
    And getUser request has been sent
    Then response doesn't contain reserved book