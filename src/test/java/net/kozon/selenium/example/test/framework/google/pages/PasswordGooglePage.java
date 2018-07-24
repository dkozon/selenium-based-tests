package net.kozon.selenium.example.test.framework.google.pages;

import net.kozon.selenium.example.test.framework.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class PasswordGooglePage extends BasePage<PasswordGooglePage> {

    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "profileIdentifier")
    private WebElement emailDisplayText;

    public PasswordGooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected PasswordGooglePage getThis() {
        return this;
    }

    public PasswordGooglePage nextButtonClick() {
        customWait.clickElement(passwordNextButton, 10);
        return getThis();
    }

    public PasswordGooglePage enterPassword(String password) {
        customWait.clickElement(passwordField, 10);
        passwordField.sendKeys(password);
        return getThis();
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(passwordNextButton);
    }

    public String getEmailDisplayed() {
        return customWait.createFluentWait().until((WebDriver ignored) -> emailDisplayText.getText());
    }
}
