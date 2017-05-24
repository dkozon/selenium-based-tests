package net.kozon.selenium.example.test.framework.common.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Dariusz Kozon on 26.02.2017.
 */
public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        } catch (NullPointerException e) {
            logger.warn("Missing 'driver' property. Set driver to default" ,e);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        } finally {
            webDriver.manage().window().maximize();
        }
    }

    private void setDriver() throws NullPointerException {
        if(System.getProperty("driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            webDriver = new ChromeDriver();
        } else if (System.getProperty("driver").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }
    }
}
