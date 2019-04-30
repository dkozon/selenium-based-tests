package net.kozon.selenium.common.tests;

import net.kozon.selenium.framework.commons.grid.TestOnGrid;
import net.kozon.selenium.framework.tools.owasp.ProxyStrategyFactory;
import net.kozon.selenium.framework.tools.owasp.WebDriverContext;
import net.kozon.selenium.framework.tools.utils.Configuration;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * BaseTest is responsible for setting up running browser.
 * Running with maven:
 * for Firefox use -Ddriver=firefox
 * for Chrome use -Ddriver=chrome
 * for remote (establish server and node and then run) -Ddriver=remote
 * for phantomjs -Ddriver=headless
 * for running through proxy -Ddriver=>browserNameProxy in example -Ddriver=firefoxProxy
 * </p>
 * <p>
 * If driver is not set then default configuration is Firefox (gecko driver).
 * </p>
 */
public class BaseTest {

    private static final String DRIVER = "driver";
    private static Configuration configuration = Configuration.getInstance();
    private static final String REMOTE_HOST_URL = configuration.getPropertyFromFile("remoteHostURL");
    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static ProxyStrategyFactory proxy = new ProxyStrategyFactory();
    private static WebDriverContext context = new WebDriverContext();
    private static TestOnGrid testOnGrid = new TestOnGrid();
    private static boolean doesGridActive = Boolean.parseBoolean(configuration.getPropertyFromFile("localhostGridEnabled"));
    protected WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        } catch (InvalidParameterException | IOException e) {
            logger.warn("Missing 'driver' property. Set driver to default");
            Configuration.setProperty(DRIVER, "firefox");
            Configuration.setProperty("webdriver.gecko.driver", configuration.getPropertyFromFile("geckoDriver"));
            webDriver = new FirefoxDriver();
        } finally {
            if (!"remote".equals(Configuration.getProperty(DRIVER))) {
                Objects.requireNonNull(webDriver).manage().window().maximize();
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
            case "chromeProxy":
                setChromeDriverThroughProxy();
                break;
            case "firefoxProxy":
                setGeckoDriverThroughProxy();
                break;
            case "edgeProxy":
                setEdgeDriverThroughProxy();
                break;
        }
    }

    private void setUpGrid() {
        if (doesGridActive) {
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
        if (doesGridActive) {
            testOnGrid.stopNode();
            testOnGrid.stopHub();
            logger.info("Grid closed!");
        }
    }

    private void setChromeDriver() {
        Configuration.setProperty("webdriver.chrome.driver", configuration.getPropertyFromFile("chromeDriver"));
        webDriver = new ChromeDriver();
    }

    private void setGeckoDriver() {
        Configuration.setProperty("webdriver.gecko.driver", configuration.getPropertyFromFile("geckoDriver"));
        webDriver = new FirefoxDriver();
    }

    private void setEdgeDriver() {
        // For correct working of Edge driver zoom feature should be disabled or set up to 100% only
        // http://www.winhelponline.com/blog/microsoft-edge-disable-zoom-reset-zoom-level-every-start/
        Configuration.setProperty("webdriver.edge.driver", configuration.getPropertyFromFile("edgeDriver"));
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
        Configuration.setProperty("webdriver.gecko.driver", configuration.getPropertyFromFile("geckoDriver"));
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
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, configuration.getPropertyFromFile("phantomJSDriver"));
        webDriver = new PhantomJSDriver(capabilities);
    }

    private void setChromeDriverThroughProxy() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getProxyChromeDriverStrategy()).webDriver();
    }

    private void setGeckoDriverThroughProxy() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getProxyGeckoDriverStrategy()).webDriver();
    }

    private void setEdgeDriverThroughProxy() throws IOException {
        webDriver = context.setProxyStrategy(proxy.getProxyEdgeDriverStrategy()).webDriver();
    }
}