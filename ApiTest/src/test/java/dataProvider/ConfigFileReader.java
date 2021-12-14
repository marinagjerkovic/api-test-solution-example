package dataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private static ConfigFileReader configFileReader;

    private ConfigFileReader() throws IOException {
        InputStream input = new FileInputStream("src/test/resources/configs/configuration.properties");
        properties = new Properties();
        properties.load(input);
    }

    public static ConfigFileReader getInstance() throws IOException {
        if (configFileReader == null) {
            configFileReader = new ConfigFileReader();
        }
        return configFileReader;
    }

    public String getBaseUri() {
        return properties.getProperty("baseUri");
    }

    public String getUserId() {
        return properties.getProperty("userId");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
