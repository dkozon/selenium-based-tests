package net.kozon.selenium.example.test.framework.the.internet.pageObjects;

import net.kozon.selenium.example.test.framework.common.pageObjects.BasePage;
import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dariusz_Kozon on 18-Apr-17.
 */
public class MainPage extends BasePage {

    private CustomWait customWait;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome to the Internet')]")
    private WebElement theInternetHeader;

    @FindBy(xpath = "//a[contains(text(), 'File Upload')]")
    private WebElement fileUploadLink;

    @FindBy(xpath = "//a[contains(text(), 'Drag and Drop')]")
    private WebElement dragAndDropLink;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        customWait = new CustomWait(webDriver);
    }

    public MainPage clickUploadLink() {
        fileUploadLink.click();
        return this;
    }

    public MainPage clickDragAndDropLink() {
        dragAndDropLink.click();
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(theInternetHeader);
    }
}
