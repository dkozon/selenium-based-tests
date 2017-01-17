package utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean isElementPresent(final WebElement element) {
        FluentWait<WebDriver> wait = createFluentWait().withTimeout(DEFAULT_TIMEOUT_IN_SEC, SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
