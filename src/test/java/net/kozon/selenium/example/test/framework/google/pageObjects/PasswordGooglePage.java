package net.kozon.selenium.example.test.framework.google.pageObjects;

import net.kozon.selenium.example.test.framework.common.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import net.kozon.selenium.example.test.framework.common.utils.CustomWait;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public class PasswordGooglePage extends BasePage {

    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "profileIdentifier")
    private WebElement emailDisplayText;

    public PasswordGooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PasswordGooglePage nextButtonClick() {
        passwordNextButton.click();
        return this;
    }

    public PasswordGooglePage enterPassword(String password) {
        passwordField.click();
        passwordField.sendKeys(password);
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(passwordNextButton);
    }

    public String getEmailDisplayed() {
        return customWait.createFluentWait().until((WebDriver ignored) -> emailDisplayText.getText());
    }
}
