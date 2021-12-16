package steps;

import endpoints.AccountEndpoints;
import endpoints.BooksEndpoints;
import helpers.EnvironmentData;
import helpers.TestContext;

public abstract class BaseSteps {
    TestContext testContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    public EnvironmentData getEnvironmentData() {
        return testContext.getEnvironmentData();
    }

    public AccountEndpoints getAccountEndpoints() {
        return testContext.getAccountEndpoints();
    }

    public BooksEndpoints getBooksEndpoints() {
        return testContext.getBooksEndpoints();
    }
}
