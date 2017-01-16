package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public class CustomWait {

    private static final int DEFAULT_TIMEOUT_IN_SEC = 10;
    private final WebDriver webDriver;

    public CustomWait(WebDriver driver) {
        this.webDriver = driver;
    }

    public FluentWait<WebDriver> createFluentWait() {
        return new WebDriverWait(webDriver, DEFAULT_TIMEOUT_IN_SEC);
    }

    private FluentWait<WebDriver> createWait(final int seconds) {
        return createFluentWait().withTimeout(seconds, SECONDS);
    }

    public WebElement waitForElementPresent(final By elementLocator) {
        return waitForElementPresent(elementLocator, DEFAULT_TIMEOUT_IN_SEC);
    }

    private WebElement waitForElementPresent(final By elementLocator, final int seconds) {
        FluentWait<WebDriver> wait = createWait(seconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public WebElement waitForElementPresent(final WebElement element) {
        return waitForElementPresent(element, DEFAULT_TIMEOUT_IN_SEC);
    }

    private WebElement waitForElementPresent(final WebElement element, final int seconds) {
        FluentWait<WebDriver> wait = createWait(seconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(final By locator) {
        return isElementPresent(locator, DEFAULT_TIMEOUT_IN_SEC);
    }

    private boolean isElementPresent(final By locator, final int timeoutInSeconds) {
        try {
            waitForElementPresent(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementPresent(final WebElement element) {
        return isElementPresent(element, DEFAULT_TIMEOUT_IN_SEC);
    }

    private boolean isElementPresent(final WebElement element, final int timeoutInSeconds) {
        try {
            waitForElementPresent(element, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getElementText(final WebElement element) {
        return getElementText(element, DEFAULT_TIMEOUT_IN_SEC);
    }

    public String getElementText(final WebElement element, final int timeoutInSeconds) {
        FluentWait<WebDriver> wait = createWait(timeoutInSeconds);
        return wait.until(new ExpectedCondition<String>() {

            public String apply(WebDriver input) {
                if(element.getText()==null){
                    return "";
                }
                return element.getText();
            }
        });
    }
}
