package steps;

import endpoints.Endpoints;
import helpers.EnvironmentData;
import helpers.TestContext;

public abstract class BaseSteps {
    TestContext testContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    public Endpoints getEndpoints() {
        return testContext.getEndpoints();
    }

    public EnvironmentData getEnvironmentData() {
        return testContext.getEnvironmentData();
    }
}
