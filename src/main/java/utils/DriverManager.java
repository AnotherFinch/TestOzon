package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Properties;

public class DriverManager {
    private static Properties properties = TestProperties.getInstance().getProperties();
    private static WebDriver driver = null;

    public static WebDriver getDriver(){
        if (driver == null){
            driver = createDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver createDriver() {

        switch (properties.getProperty("browser")) {
            case "opera":
                System.setProperty("webdriver.opera.driver", properties.getProperty("driver"));
                driver = new OperaDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("driver"));
                driver = new ChromeDriver();
        }
        return driver;
    }
}