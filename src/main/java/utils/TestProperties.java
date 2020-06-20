package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private Properties properties = new Properties();

    private static TestProperties INSTANCE = null;

    private TestProperties() {
        try {
            properties.load(new FileInputStream(new File("src/test/resources/" +
                    ((System.getProperty("environment") == null) ? "chrome":
                            System.getProperty("environment"))+ ".properties")));
        }  catch (IOException e) {
            System.out.println("Файл настроек не найден" + e.getMessage());
        }
    }

    public static TestProperties getInstance() {
        if (INSTANCE == null){
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }
    public Properties getProperties() {
        return properties;
    }
}