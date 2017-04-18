package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.utils.PageObjectManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dariusz Kozon on 26.02.2017.
 */
public class BaseTest {

    protected WebDriver webDriver;
    protected PageObjectManager manager;

    public BaseTest() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        webDriver = new FirefoxDriver();
        manager = new PageObjectManager(webDriver);
    }
}
