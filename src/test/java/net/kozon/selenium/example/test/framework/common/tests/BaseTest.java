package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;

/**
 * Created by Dariusz Kozon on 26.02.2017.
 *
 * BaseTest is responsible for setting up running browser.
 * Running with maven:
 * for Firefox use -Ddriver=firefox
 * for Chrome use -Ddriver=chrome
 *
 * If driver is not set then default configuration is Firefox (gecko driver).
 */
public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String DRIVER = "driver";

    protected WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        } catch (InvalidParameterException e) {
            logger.warn("Missing 'driver' property. Set driver to default", e);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        } finally {
            webDriver.manage().window().maximize();
        }
    }

    private void setDriver() throws InvalidParameterException {
        if(Configuration.getProperty(DRIVER).equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            webDriver = new ChromeDriver();
        } else if (Configuration.getProperty(DRIVER).equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }
    }
}
