package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppReader {
    private static Properties properties;

    static {
        String bankAppFile = "src/test/resources/properties/bankapplication.properties";

        FileInputStream input = null;

        try {
            input = new FileInputStream(bankAppFile);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
        finally {
            try {
                assert input != null;
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPropertyValue(String value) {
        return properties.getProperty(value);
    }
}
