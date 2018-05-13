package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.grid.TestOnGrid;
import net.kozon.selenium.example.test.framework.common.owasp.ProxyStrategyFactory;
import net.kozon.selenium.example.test.framework.common.owasp.WebDriverContext;
import net.kozon.selenium.example.test.framework.common.utils.Configuration;
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
import java.util.concurrent.TimeUnit;

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
    private static ProxyStrategyFactory proxy = new ProxyStrategyFactory();
    private static WebDriverContext context = new WebDriverContext();
    private static TestOnGrid testOnGrid = new TestOnGrid();
    private static boolean doesGridActive = Boolean.parseBoolean(Configuration.getPropertyFromFile("localhostGridEnabled"));

    private static final String DRIVER = "driver";
    private static final String REMOTE_HOST_URL = Configuration.getPropertyFromFile("remoteHostURL");

    protected WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        }catch (InvalidParameterException | IOException e){
            logger.warn("Missing 'driver' property. Set driver to default");
            Configuration.setProperty(DRIVER, "firefox");
            Configuration.setProperty("webdriver.gecko.driver", Configuration.getPropertyFromFile("geckoDriver"));
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
                setChromeDriver();
                break;
            case "firefox":
                setGeckoDriver();
                break;
            case "edge":
                setEdgeDriver();
                break;
            case "remote":
                setRemoteDriver();
                break;
            case "remoteOnLocalhost":
                setRemoteOnLocalhost();
                break;
            case "headless":
                setHeadless();
                break;
            case "chromeZAP":
                setChromeDriverThroughZAP();
                break;
            case "firefoxZAP":
                setGeckoDriverThroughZAP();
                break;
            case "edgeZAP":
                setEdgeDriverThroughZAP();
                break;
        }
    }

    private void setUpGrid() {
        if(doesGridActive) {
            testOnGrid.runHub();
            testOnGrid.runNode();
            try {
                TimeUnit.SECONDS.sleep(10); // needed for solve problem with Session expiring during execution ong grid
            } catch (InterruptedException e) {
                logger.error("Timeout corrupted! ", e);
            }
            logger.info("Grid set up and ready for test processing!");
        }
    }

    protected void tearDownGridIfNeeded() {
        if(doesGridActive) {
            testOnGrid.stopNode();
            testOnGrid.stopHub();
            logger.info("Grid closed!");
        }
    }

    private void setChromeDriver() {
        Configuration.setProperty("webdriver.chrome.driver", Configuration.getPropertyFromFile("chromeDriver"));
        webDriver = new ChromeDriver();
    }

    private void setGeckoDriver() {
        Configuration.setProperty("webdriver.gecko.driver", Configuration.getPropertyFromFile("geckoDriver"));
        webDriver = new FirefoxDriver();
    }

    private void setEdgeDriver() {
        // For correct working of Edge driver zoom feature should be disabled or set up to 100% only
        // http://www.winhelponline.com/blog/microsoft-edge-disable-zoom-reset-zoom-level-every-start/
        Configuration.setProperty("webdriver.edge.driver", Configuration.getPropertyFromFile("edgeDriver"));
        webDriver = new EdgeDriver();
    }

    private void setRemoteDriver() {
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(REMOTE_HOST_URL), DesiredCapabilities.firefox());
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            webDriver = remoteWebDriver;
        } catch (MalformedURLException e) {
            logger.error("Missing RemoteWebDriver instance! ", e);
        }
    }

    private void setRemoteOnLocalhost() {
        Configuration.setProperty("webdriver.gecko.driver", Configuration.getPropertyFromFile("geckoDriver"));
        setUpGrid();
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(REMOTE_HOST_URL), DesiredCapabilities.firefox());
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            webDriver = remoteWebDriver;
        } catch (MalformedURLException e) {
            logger.error("Missing RemoteWebDriver instance! ", e);
        }
    }

    private void setHeadless() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,Configuration.getPropertyFromFile("phantomJSDriver"));
        webDriver = new PhantomJSDriver(capabilities);
    }

    private void setChromeDriverThroughZAP() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getOwaspProxyChromeDriverStrategy()).webDriver();
    }

    private void setGeckoDriverThroughZAP() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getOwaspProxyGeckoDriverStrategy()).webDriver();
    }

    private void setEdgeDriverThroughZAP() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getOwaspProxyEdgeDriverStrategy()).webDriver();
    }
}