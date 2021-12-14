package steps;

import helpers.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.requests.Isbn;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import model.responses.Book;
import model.responses.Books;
import model.responses.UserAccount;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class BooksSteps extends BaseSteps {
    public BooksSteps(TestContext testContext) {
        super(testContext);
    }

    @And("returnAllBooks request has been sent")
    public void returnAllBooks_request_has_been_sent() {
        String userId = getEnvironmentProperties().getUserId();

        Response response = getEndpoints().returnAllBooks(userId);
        assertEquals(response.statusCode(), 204);
        getEnvironmentProperties().setResponse(response);
    }

    @When("getAllBooks request has been sent")
    public void getAllBooks_request_has_been_sent() {
        Response response = getEndpoints().getAllBooks();

        assertEquals(response.statusCode(), 200);
        getEnvironmentProperties().setResponse(response);
    }

    @Then("response contains not empty list of books")
    public void response_contains_not_empty_list_of_books() {
        Books allBooks = getEnvironmentProperties().getResponse().getBody().as(Books.class);
        assertFalse(allBooks.getBooks().isEmpty());
    }

    @When("reserveBooks request for first book has been sent")
    public void reserveBooks_request_for_first_book_has_been_sent() {
        Books allBooks = getEnvironmentProperties().getResponse().getBody().as(Books.class);
        Book firstBook = allBooks.getBooks().get(0);
        getEnvironmentProperties().setReservedBook(firstBook);

        String userId = getEnvironmentProperties().getUserId();
        Isbn isbn = new Isbn(firstBook.getIsbn());
        ReserveBooksRequest reserveBooksRequest = new ReserveBooksRequest(userId, isbn);

        Response response = getEndpoints().reserveBooks(reserveBooksRequest);
        assertEquals(response.statusCode(), 201);
        getEnvironmentProperties().setResponse(response);
    }

    @When("getUser request has been sent")
    public void getUser_request_has_been_sent() {
        String userId = getEnvironmentProperties().getUserId();

        Response response = getEndpoints().getUser(userId);
        assertEquals(response.statusCode(), 200);
        getEnvironmentProperties().setResponse(response);
    }

    @Then("response contains reserved book")
    public void response_contains_reserved_book() {
        UserAccount userAccount = getEnvironmentProperties().getResponse().as(UserAccount.class);
        Book reservedBook = getEnvironmentProperties().getReservedBook();
        boolean exists = doesBookExistInUsersBooks(userAccount.getBooks(), reservedBook);
        assertTrue(exists);
    }

    @When("returnBook request has been sent")
    public void returnBook_request_has_been_sent() {
        String userId = getEnvironmentProperties().getUserId();
        String isbn = getEnvironmentProperties().getReservedBook().getIsbn();
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(userId, isbn);

        Response response = getEndpoints().returnBook(returnBookRequest);
        assertEquals(response.statusCode(), 204);
        getEnvironmentProperties().setResponse(response);
    }

    @Then("response doesn't contain reserved book")
    public void response_doesnt_contain_reserved_book() {
        UserAccount userAccount = getEnvironmentProperties().getResponse().as(UserAccount.class);
        Book reservedBook = getEnvironmentProperties().getReservedBook();
        boolean exists = doesBookExistInUsersBooks(userAccount.getBooks(), reservedBook);
        assertFalse(exists);
    }

    public boolean doesBookExistInUsersBooks(ArrayList<Book> usersBooks, Book reservedBook) {
        for (Book book: usersBooks) {
            if (book.getIsbn().equals(reservedBook.getIsbn())) {
                return true;
            }
        }
        return false;
    }
}
