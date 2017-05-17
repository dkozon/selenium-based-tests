package net.kozon.selenium.example.test.framework.the.internet.pageObjects;

import net.kozon.selenium.example.test.framework.common.pageObjects.BasePage;
import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Dariusz_Kozon on 18-Apr-17.
 */
public class TheInternetMainPage extends BasePage {

    private CustomWait customWait;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome to the Internet')]")
    private WebElement theInternetHeader;

    @FindBy(xpath = "//a[contains(text(), 'File Upload')]")
    public WebElement fileUploadLink;

    public TheInternetMainPage(WebDriver webDriver) {
        super(webDriver);
        customWait = new CustomWait(webDriver);
    }

    public TheInternetMainPage clickUploadLink() {
        fileUploadLink.click();
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(theInternetHeader);
    }
}
