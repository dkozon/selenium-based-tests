package net.kozon.selenium.example.test.framework.google.pageObjects;

import net.kozon.selenium.example.test.framework.common.pageObjects.BasePage;
import net.kozon.selenium.example.test.framework.common.utils.CustomWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class LoginGooglePage extends BasePage {

    @FindBy(xpath = ".//input[@id='identifierId']")
    private WebElement emailField;

    @FindBy(id = "identifierNext")
    private WebElement nextButton;

    public LoginGooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginGooglePage enterMailAddress(String login) {
        emailField.click();
        emailField.sendKeys(login);
        return this;
    }

    public LoginGooglePage clickNextButton() {
        nextButton.click();
        return this;
    }

    public LoginGooglePage alternateClickNextButton() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", nextButton);
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(emailField) && customWait.isElementPresent(nextButton);
    }

}
