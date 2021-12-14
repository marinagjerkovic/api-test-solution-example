Feature: Book reservation

  Background: User is authorized
    Given I am an authorized user

  Scenario: Authorized user can reserve and return a book
    And I don't have any book reserved
    And a list of books are available
    When I reserve a book
    Then the book is added to my reading list
    When I return the book
    Then the book is removed from my reading list