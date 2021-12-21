package helperData;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import requestHandlers.AccountRequestHandler;
import requestHandlers.BooksRequestHandler;
import requestHandlers.RequestSpecificationManager;

import java.io.File;
import java.io.IOException;

@Getter
public class TestContext {
    private RequestResponseData requestResponseData;

    private AccountRequestHandler accountRequestHandler;
    private BooksRequestHandler booksRequestHandler;

    public TestContext() {
        requestResponseData = new RequestResponseData();
        accountRequestHandler = new AccountRequestHandler(requestResponseData);
        booksRequestHandler = new BooksRequestHandler(requestResponseData);
    }
}
