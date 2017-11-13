package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.owasp.ProxyStrategyFactory;
import net.kozon.selenium.example.test.framework.common.utils.Configuration;
import net.kozon.selenium.example.test.framework.common.owasp.WebDriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
 * for phantomjs -Ddriver=headless
 * for Owasp ZAP proxy -Ddriver=>browserNameZAP in example -Ddriver=firefoxZAP
 *
 * If driver is not set then default configuration is Firefox (gecko driver).
 */
public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String DRIVER = "driver";
    private static String GRID_IP = "192.168.99.100";
    private static final String REMOTE_HOST_URL = "http://%s:4444/wd/hub";
    private static DesiredCapabilities capabilities;
    private static ProxyStrategyFactory proxy = new ProxyStrategyFactory();
    private static WebDriverContext context = new WebDriverContext();

    protected WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        }catch (InvalidParameterException | IOException e){
            logger.warn("Missing 'driver' property. Set driver to default");
            Configuration.setProperty(DRIVER, "firefox");
            Configuration.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            webDriver = new FirefoxDriver();
        }
        finally {
            switch(Configuration.getProperty(DRIVER)){
                case "remote":
                    break;
                default:
                    webDriver.manage().window().maximize();
                    break;
            }
        }
    }

    private void setDriver() throws InvalidParameterException, IOException {
        switch (Configuration.getProperty(DRIVER)) {
            case "chrome":
                Configuration.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                Configuration.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                // For correct working of Edge driver zoom feature should be disabled or set up to 100% only
                // http://www.winhelponline.com/blog/microsoft-edge-disable-zoom-reset-zoom-level-every-start/
                Configuration.setProperty("webdriver.edge.driver", "src/test/resources/MicrosoftWebDriver16299.exe");
                webDriver = new EdgeDriver();
                break;
            case "remote":
                capabilities = DesiredCapabilities.firefox();
                try {
                    RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(String.format(REMOTE_HOST_URL, GRID_IP)), capabilities);
                    remoteWebDriver.setFileDetector(new LocalFileDetector());
                    webDriver = remoteWebDriver;
                } catch (MalformedURLException e) {
                    logger.error("Missing RemoteWebDriver instance! ", e);
                }
                break;
            case "headless":
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"src/test/resources/phantomjs.exe");
                webDriver = new PhantomJSDriver(capabilities);
                break;
            case "chromeZAP":
                webDriver = context.setProxyStrategy(proxy.getOwaspProxyChromeDriverStrategy()).webDriver();
                break;
            case "firefoxZAP":
                webDriver = context.setProxyStrategy(proxy.getOwaspProxyGeckoDriverStrategy()).webDriver();
                break;
            case "edgeZAP":
                webDriver = context.setProxyStrategy(proxy.getOwaspProxyEdgeDriverStrategy()).webDriver();
                break;
        }
    }
}