package net.kozon.selenium.example.test.framework.common.tests;

import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dariusz Kozon on 26.02.2017.
 */
public class BaseTest {

    protected WebDriver webDriver;

    public BaseTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }
}
