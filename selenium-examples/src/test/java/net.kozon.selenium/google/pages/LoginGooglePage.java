package net.kozon.selenium.google.pages;

import net.kozon.selenium.common.pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class LoginGooglePage extends BasePage<LoginGooglePage> {

    @FindBy(xpath = ".//input[@id='identifierId']")
    private WebElement emailField;

    @FindBy(id = "identifierNext")
    private WebElement nextButton;

    public LoginGooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected LoginGooglePage getThis() {
        return this;
    }

    public LoginGooglePage enterMailAddress(String login) {
        customWait.clickElement(emailField);
        emailField.sendKeys(login);
        return this;
    }

    public LoginGooglePage clickNextButton() {
        customWait.clickElement(nextButton);
        return this;
    }

    public LoginGooglePage alternateClickNextButton() {
        customWait.isElementVisible(nextButton);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", nextButton);
        return getThis();
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(emailField) && customWait.isElementVisible(nextButton);
    }

}
