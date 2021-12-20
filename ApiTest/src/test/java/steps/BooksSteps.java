package steps;

import helperData.RequestResponseData;
import helperData.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.entities.Isbn;
import model.requests.ReserveBooksRequest;
import model.requests.ReturnBookRequest;
import model.entities.Book;
import model.responses.Books;
import model.responses.UserAccount;
import requestHandlers.BooksRequestHandler;
import requestHandlers.RequestSpecificationManager;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class BooksSteps extends BaseSteps {
    public BooksSteps(TestContext testContext) {
        super(testContext);
    }

    @And("returnAllBooks request has been sent")
    public void returnAllBooks_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();
        Response response = testContext.getBooksRequestHandler().returnAllBooks(userId);
        testContext.getRequestResponseData().setResponse(response);
    }

    @When("getAllBooks request has been sent")
    public void getAllBooks_request_has_been_sent() {
        Response response = testContext.getBooksRequestHandler().getAllBooks();
        testContext.getRequestResponseData().setResponse(response);
    }

    @Then("response contains not empty list of books")
    public void response_contains_not_empty_list_of_books() {
        Books allBooks = testContext.getRequestResponseData().getResponse().getBody().as(Books.class);
        assertFalse(allBooks.getBooks().isEmpty());
        testContext.getRequestResponseData().setReservedBook(allBooks.getBooks().get(0));
    }

    @When("reserveBooks request has been sent")
    public void reserveBooks_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();
        Book firstBook = testContext.getRequestResponseData().getReservedBook();
        Isbn isbn = new Isbn(firstBook.getIsbn());
        ReserveBooksRequest reserveBooksRequest = new ReserveBooksRequest(userId, isbn);

        Response response = testContext.getBooksRequestHandler().reserveBooks(reserveBooksRequest);
        testContext.getRequestResponseData().setResponse(response);
    }

    @When("getUser request has been sent")
    public void getUser_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();

        Response response = testContext.getBooksRequestHandler().getUser(userId);
        testContext.getRequestResponseData().setResponse(response);
    }

    @Then("response contains reserved book")
    public void response_contains_reserved_book() {
        UserAccount userAccount = testContext.getRequestResponseData().getResponse().getBody().as(UserAccount.class);
        Book reservedBook = testContext.getRequestResponseData().getReservedBook();
        boolean exists = doesBookExistInUsersBooks(userAccount.getBooks(), reservedBook);
        assertTrue(exists);
    }

    @When("returnBook request has been sent")
    public void returnBook_request_has_been_sent() {
        String userId = getEnvironmentData().getUserId();
        String isbn = testContext.getRequestResponseData().getReservedBook().getIsbn();
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(userId, isbn);

        Response response = testContext.getBooksRequestHandler().returnBook(returnBookRequest);
        testContext.getRequestResponseData().setResponse(response);
    }

    @Then("response doesn't contain reserved book")
    public void response_doesnt_contain_reserved_book() {
        UserAccount userAccount = testContext.getRequestResponseData().getResponse().getBody().as(UserAccount.class);
        Book reservedBook = testContext.getRequestResponseData().getReservedBook();
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
