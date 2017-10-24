package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;


/**
 * Created by Dariusz Kozon on 26.02.2017.
 *
 * BaseTest is responsible for setting up running browser.
 * Running with maven:
 * for Firefox use -Ddriver=firefox
 * for Chrome use -Ddriver=chrome
 * for remote (establish server and node and then run) -Ddriver=remote
 *
 * If driver is not set then default configuration is Firefox (gecko driver).
 */
public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String DRIVER = "driver";
    private static final String REMOTE_HOST_URL = "http://localhost:4444/wd/hub";

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
        } else if (Configuration.getProperty(DRIVER).equals("remote")) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            try {
                webDriver = new RemoteWebDriver(new URL(REMOTE_HOST_URL), capabilities);
            } catch (MalformedURLException e) {
                logger.error("Missing RemoteWebDriver! ", e);
            }
        }
    }
}
