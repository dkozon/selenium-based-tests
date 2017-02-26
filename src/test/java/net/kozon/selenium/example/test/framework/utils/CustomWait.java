package net.kozon.selenium.example.test.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public class CustomWait {

    private static Logger logger = LoggerFactory.getLogger(CustomWait.class);
    private static final int DEFAULT_TIMEOUT_IN_SEC = 10;
    private final WebDriver webDriver;

    public CustomWait(WebDriver driver) {
        this.webDriver = driver;
    }

    public FluentWait<WebDriver> createFluentWait() {
        return new WebDriverWait(webDriver, DEFAULT_TIMEOUT_IN_SEC);
    }

    public boolean isElementPresent(final WebElement element) {
        FluentWait<WebDriver> wait = createFluentWait().withTimeout(DEFAULT_TIMEOUT_IN_SEC, SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            logger.error("Element not found!", e);
            return false;
        }
    }

    public boolean isElementClicable(final WebElement element) {
        FluentWait<WebDriver> wait = createFluentWait().withTimeout(DEFAULT_TIMEOUT_IN_SEC, SECONDS);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            logger.error("Element not found!", e);
            return false;
        }
    }

    public boolean isElementDisappear(final By by) {
        FluentWait<WebDriver> wait = createFluentWait().withTimeout(DEFAULT_TIMEOUT_IN_SEC, SECONDS);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            logger.error("Element should not be visible!", e);
            return false;
        }
    }
}
