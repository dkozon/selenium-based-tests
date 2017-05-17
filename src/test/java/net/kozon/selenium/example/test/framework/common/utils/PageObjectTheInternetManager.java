package net.kozon.selenium.example.test.framework.common.utils;

import net.kozon.selenium.example.test.framework.the.internet.pageObjects.TheInternetMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public class PageObjectTheInternetManager {

    private final WebDriver webDriver;
    private TheInternetMainPage theInternetMainPage;

    public PageObjectTheInternetManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public TheInternetMainPage theInternetMainPage() {
        if (theInternetMainPage == null) {
            theInternetMainPage = PageFactory.initElements(webDriver, TheInternetMainPage.class);
        }
        return theInternetMainPage;
    }
}
