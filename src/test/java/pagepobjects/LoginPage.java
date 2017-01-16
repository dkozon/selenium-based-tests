package pagepobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;
import utils.CustomWait;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = ".//input[@id='Email']")
    private WebElement emailField;

    @FindBy(id = "next")
    private WebElement nextButton;

    private CustomWait customWait;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        customWait = new CustomWait(webDriver);
    }

    public LoginPage enterMailAddress(String login) {
        emailField.sendKeys(login);
        return this;
    }

    public LoginPage clickNextButton() {
        nextButton.click();
        return this;
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementPresent(emailField) && customWait.isElementPresent(nextButton);
    }

}
