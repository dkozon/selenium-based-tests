package net.kozon.selenium.example.test.framework.common.pageObjects;

import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import net.kozon.selenium.example.test.framework.internet.pageObjects.FileUploadPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public abstract class BasePage<T extends BasePage<T>> {

    public WebDriver webDriver;
    protected CustomWait customWait;

    protected abstract T getThis();

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        customWait = new CustomWait(webDriver);
    }

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
