package steps;

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
}
