package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.AccountEndpoints;
import endpoints.BooksEndpoints;
import endpoints.RequestSpecificationManager;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class TestContext {
    private EnvironmentData environmentData;

    private AccountEndpoints accountEndpoints;
    private BooksEndpoints booksEndpoints;

    public TestContext() throws IOException {
        String environment = System.getProperty("env");
        if (environment == null) environment = "dev";

        ObjectMapper mapper = new ObjectMapper();
        environmentData = mapper.readValue(new File("src/test/resources/configs/" + environment + "-env.json"), EnvironmentData.class);

        String baseUri = environmentData.getBaseUri();
        environmentData.setRequestSpecification(RequestSpecificationManager.create(baseUri));

        accountEndpoints = new AccountEndpoints(environmentData.getRequestSpecification());
        booksEndpoints = new BooksEndpoints(environmentData.getRequestSpecification());
    }
}
