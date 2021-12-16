package helperData;

import com.fasterxml.jackson.databind.ObjectMapper;
import requestHandlers.AccountRequestHandler;
import requestHandlers.BooksRequestHandler;
import requestHandlers.RequestSpecificationManager;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class TestContext {
    private EnvironmentData environmentData;
    private RequestResponseData requestResponseData;

    private AccountRequestHandler accountRequestHandler;
    private BooksRequestHandler booksRequestHandler;

    public TestContext(RequestResponseData requestResponseData) throws IOException {
        this.requestResponseData = requestResponseData;

        String environment = System.getProperty("env");
        if (environment == null) environment = "dev";

        ObjectMapper mapper = new ObjectMapper();
        environmentData = mapper.readValue(new File("src/test/resources/configs/" + environment + "-env.json"), EnvironmentData.class);

        String baseUri = environmentData.getBaseUri();
        this.requestResponseData.setRequestSpecification(RequestSpecificationManager.create(baseUri));

        accountRequestHandler = new AccountRequestHandler(requestResponseData.getRequestSpecification());
        booksRequestHandler = new BooksRequestHandler(requestResponseData.getRequestSpecification());
    }
}
