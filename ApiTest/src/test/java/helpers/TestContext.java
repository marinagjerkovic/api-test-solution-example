package helpers;

import dataProvider.ConfigFileReader;
import endpoints.Endpoints;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;

@Getter
public class TestContext {
    private Endpoints endpoints;

    private HashMap<ContextKey, Object> scenarioContext;

    public TestContext() throws IOException {
        endpoints = new Endpoints(ConfigFileReader.getInstance().getBaseUri());

        scenarioContext = new HashMap<>();
        scenarioContext.put(ContextKey.USER_ID, ConfigFileReader.getInstance().getUserId());
        scenarioContext.put(ContextKey.USERNAME, ConfigFileReader.getInstance().getUsername());
        scenarioContext.put(ContextKey.PASSWORD, ConfigFileReader.getInstance().getPassword());
    }
}
