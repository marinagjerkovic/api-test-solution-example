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
    private EnvironmentData environmentData;
    private RequestResponseData requestResponseData;

    private AccountRequestHandler accountRequestHandler;
    private BooksRequestHandler booksRequestHandler;

    public TestContext() throws IOException {
        String environment = System.getProperty("env");
        if (environment == null) environment = "dev";

        ObjectMapper mapper = new ObjectMapper();
        environmentData = mapper.readValue(new File("src/test/resources/configs/" + environment + "-env.json"), EnvironmentData.class);


        requestResponseData = new RequestResponseData();
        accountRequestHandler = new AccountRequestHandler(environmentData, requestResponseData);
        booksRequestHandler = new BooksRequestHandler(environmentData, requestResponseData);
    }
}
