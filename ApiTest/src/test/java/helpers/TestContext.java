package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.Endpoints;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class TestContext {
    private Endpoints endpoints;

    private EnvironmentData environmentData;

    public TestContext() throws IOException {
        String environment = System.getProperty("env");
        if (environment == null) environment = "dev";

        ObjectMapper mapper = new ObjectMapper();
        environmentData = mapper.readValue(new File("src/test/resources/configs/" + environment + "-env.json"), EnvironmentData.class);

        endpoints = new Endpoints(environmentData.getBaseUri());
    }
}
