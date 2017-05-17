package net.kozon.selenium.example.test.framework.common.pageObjects;

import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public abstract class BasePage {

    public WebDriver webDriver;
    protected CustomWait customWait;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        customWait = new CustomWait(webDriver);
    }

    public abstract boolean isLoaded();

    public BasePage loadPage(String url) {
        webDriver.get(url);
        return this;
    }
}
