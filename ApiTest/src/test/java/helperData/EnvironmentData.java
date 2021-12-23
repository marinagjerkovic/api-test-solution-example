package helperData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Getter
public class EnvironmentData {
    public static String userId;
    public static String baseUri;
    public static String username;
    public static String password;

    public static void setValues() throws IOException {
        String environment = System.getProperty("env");
        if (environment == null) environment = "dev";

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> environmentDataValues = mapper.readValue(new File("src/test/resources/configs/" + environment + "-env.json"), new TypeReference<Map<String, Object>>(){});

        EnvironmentData.userId = (String) environmentDataValues.get("userId");
        EnvironmentData.baseUri = (String) environmentDataValues.get("baseUri");
        EnvironmentData.username = (String) environmentDataValues.get("username");
        EnvironmentData.password = (String) environmentDataValues.get("password");
    }
}
