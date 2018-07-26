package net.kozon.selenium.example.tests.common.pages;

import net.kozon.selenium.framework.tools.utils.CustomWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage<T extends BasePage<T>> {

    public WebDriver webDriver;
    protected CustomWait customWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        customWait = new CustomWait(webDriver);
    }

    protected abstract T getThis();

    public abstract boolean isLoaded();

    //one of the solutions for problem with not clickable element
    public T makeElementScaled(WebElement uploadButton) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.transform='scale(1)';", uploadButton);
        return getThis();
    }

    public BasePage loadPage(String url) {
        webDriver.get(url);
        return this;
    }
}
