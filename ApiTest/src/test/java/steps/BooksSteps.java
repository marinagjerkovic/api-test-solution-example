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
        String userId = getEnvironmentData().getUserId();

        Response response = getEndpoints().returnAllBooks(userId);
        assertEquals(response.statusCode(), 204);
    }

    @When("getAllBooks request has been sent")
    public void getAllBooks_request_has_been_sent() {
        Response response = getEndpoints().getAllBooks();

        assertEquals(response.statusCode(), 200);
        getEnvironmentData().setResponse(response);
    }

    @Then("response contains not empty list of books")
    public void response_contains_not_empty_list_of_books() {
        Books allBooks = getEnvironmentData().getResponse().getBody().as(Books.class);
        assertFalse(allBooks.getBooks().isEmpty());
        getEnvironmentData().setReservedBook(allBooks.getBooks().get(0));
    }

    @When("reserveBooks request has been sent")
    public void reserveBooks_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();
        Book firstBook = getEnvironmentData().getReservedBook();
        Isbn isbn = new Isbn(firstBook.getIsbn());
        ReserveBooksRequest reserveBooksRequest = new ReserveBooksRequest(userId, isbn);

        Response response = getEndpoints().reserveBooks(reserveBooksRequest);
        assertEquals(response.statusCode(), 201);
    }

    @When("getUser request has been sent")
    public void getUser_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();

        Response response = getEndpoints().getUser(userId);
        assertEquals(response.statusCode(), 200);
        getEnvironmentData().setResponse(response);
    }

    @Then("response contains reserved book")
    public void response_contains_reserved_book() {
        UserAccount userAccount = getEnvironmentData().getResponse().as(UserAccount.class);
        Book reservedBook = getEnvironmentData().getReservedBook();
        boolean exists = doesBookExistInUsersBooks(userAccount.getBooks(), reservedBook);
        assertTrue(exists);
    }

    @When("returnBook request has been sent")
    public void returnBook_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();
        String isbn = getEnvironmentData().getReservedBook().getIsbn();
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(userId, isbn);

        Response response = getEndpoints().returnBook(returnBookRequest);
        assertEquals(response.statusCode(), 204);
    }

    @Then("response doesn't contain reserved book")
    public void response_doesnt_contain_reserved_book() {
        UserAccount userAccount = getEnvironmentData().getResponse().as(UserAccount.class);
        Book reservedBook = getEnvironmentData().getReservedBook();
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
