package pagepobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;
import utils.CustomWait;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public class PasswordPage extends BasePage{

    @FindBy(id = "signIn")
    private WebElement signInButton;

    @FindBy(id = "Passwd")
    private WebElement passwordField;

    @FindBy(id = "PersistentCookie")
    private WebElement staySignedInCheckbox;

    @FindBy(id = "email-display")
    private WebElement emailDisplayText;

    private CustomWait customWait;

    public PasswordPage(WebDriver webDriver) {
        super(webDriver);
        customWait = new CustomWait(webDriver);
    }

    public PasswordPage signInButtonClick() {
        signInButton.click();
        return this;
    }

    public PasswordPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(signInButton);
    }

    public String getEmailDisplayed() {
        return customWait.createFluentWait().until((WebDriver ignored) -> emailDisplayText.getText());
    }

    public boolean isStaySignedInCheckboxSelected() {
        return staySignedInCheckbox.isSelected();
    }
}
