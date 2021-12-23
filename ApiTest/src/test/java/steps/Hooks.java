package steps;

import helperData.EnvironmentData;
import io.cucumber.java.BeforeAll;

import java.io.IOException;

public class Hooks {

    @BeforeAll
    public static void loadData() throws IOException {
        EnvironmentData.setValues();
    }
}
