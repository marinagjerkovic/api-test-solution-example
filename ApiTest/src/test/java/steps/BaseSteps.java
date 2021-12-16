package steps;

import helperData.RequestResponseData;
import requestHandlers.AccountRequestHandler;
import requestHandlers.BooksRequestHandler;
import helperData.EnvironmentData;
import helperData.TestContext;

public abstract class BaseSteps {
    TestContext testContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    public EnvironmentData getEnvironmentData() {
        return testContext.getEnvironmentData();
    }

    public RequestResponseData getRequestResponseData() {
        return testContext.getRequestResponseData();
    }

    public AccountRequestHandler getAccountRequestHandler() {
        return testContext.getAccountRequestHandler();
    }

    public BooksRequestHandler getBooksRequestHandler() {
        return testContext.getBooksRequestHandler();
    }
}
