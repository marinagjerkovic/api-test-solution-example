package steps;

import helpers.ContextKey;
import helpers.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.requests.Isbn;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import model.responses.Book;
import model.responses.Books;
import model.responses.UserAccount;

import static org.testng.Assert.*;

public class BooksSteps extends BaseSteps {
    public BooksSteps(TestContext testContext) {
        super(testContext);
    }

    @And("I don't have any book reserved")
    public void iDontHaveAnyBookReserved() {
        String userId = (String) getScenarioContext().get(ContextKey.USER_ID);

        Response response = getEndpoints().returnAllBooks(userId);
        assertEquals(response.statusCode(), 204);
    }


    @And("a list of books are available")
    public void aListOfBooksAreAvailable() {
        Response response = getEndpoints().getAllBooks();
        assertEquals(response.statusCode(), 200);

        Books allBooks = response.getBody().as(Books.class);
        assertTrue(allBooks.books.size() > 0);
        Book firstBook = allBooks.books.get(0);
        getScenarioContext().put(ContextKey.BOOK, firstBook);
    }

    @When("I reserve a book")
    public void iReserveABook() {
        String userId = (String) getScenarioContext().get(ContextKey.USER_ID);
        Isbn bookIsbn = new Isbn(((Book) getScenarioContext().get(ContextKey.BOOK)).isbn);
        ReserveBooksRequest reserveBooksRequest = new ReserveBooksRequest(userId, bookIsbn);

        Response response = getEndpoints().reserveBooks(reserveBooksRequest);
        assertEquals(response.statusCode(), 201);
    }

    @Then("the book is added to my reading list")
    public void theBookIsInMyReadingList() {
        boolean exists = doesBookExistInUsersBooks();
        assertTrue(exists);
    }

    @When("I return the book")
    public void iReturnTheBook() {
        String userId = (String) getScenarioContext().get(ContextKey.USER_ID);
        String bookIsbn = ((Book) getScenarioContext().get(ContextKey.BOOK)).isbn;
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(userId, bookIsbn);

        Response response = getEndpoints().returnBook(returnBookRequest);
        assertEquals(response.statusCode(), 204);
    }

    @Then("the book is removed from my reading list")
    public void theBookIsNotInMyReadingList() {
        boolean exists = doesBookExistInUsersBooks();
        assertFalse(exists);
    }

    public boolean doesBookExistInUsersBooks() {
        String userId = (String) getScenarioContext().get(ContextKey.USER_ID);

        Response response = getEndpoints().getUser(userId);
        assertEquals(response.statusCode(), 200);

        UserAccount userAccount = response.getBody().as(UserAccount.class);
        String bookIsbn = ((Book) getScenarioContext().get(ContextKey.BOOK)).isbn;
        for (Book usersBook: userAccount.books) {
            if (usersBook.isbn.equals(bookIsbn)) {
                return true;
            }
        }
        return false;
    }
}
