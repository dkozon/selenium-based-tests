package net.kozon.selenium.example.test.framework.pageObjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public abstract class BasePage {

    private WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public abstract boolean isLoaded();

    public BasePage loadPage(String url) {
        webDriver.get(url);
        return this;
    }
}