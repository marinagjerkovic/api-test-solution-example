package steps;

import endpoints.Endpoints;
import helpers.ContextKey;
import helpers.TestContext;

import java.util.HashMap;

public abstract class BaseSteps {
    TestContext testContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    public Endpoints getEndpoints() {
        return testContext.getEndpoints();
    }

    public HashMap<ContextKey, Object> getScenarioContext() {
        return testContext.getScenarioContext();
    }
}
