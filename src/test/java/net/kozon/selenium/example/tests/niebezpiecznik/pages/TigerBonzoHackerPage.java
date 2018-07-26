package net.kozon.selenium.example.tests.niebezpiecznik.pages;

import net.kozon.selenium.example.tests.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TigerBonzoHackerPage extends BasePage<TigerBonzoHackerPage> {

    @FindBy(xpath = ".//h1[contains(text('Haker Tiger Bonzo'))]")
    private WebElement tigerBonzoHeader;

    @FindBy(xpath = ".//form/input[@class='tiger_przepraszam']")
    private WebElement tigerBonzoQuestionField;

    @FindBy(xpath = "/html/body/div/div[3]/div/div[3]/div[1]/form/input[2]") //example of non optimized xpath
    private WebElement submitButton;

    private String infoSentMessage = "//span[contains(text(),'%s')]";

    public TigerBonzoHackerPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected TigerBonzoHackerPage getThis() {
        return this;
    }

    public TigerBonzoHackerPage fillFieldForQuestion(String question) {
        tigerBonzoQuestionField.sendKeys(question);
        return getThis();
    }

    public TigerBonzoHackerPage clickChceckButton() {
        customWait.clickElement(submitButton);
        return getThis();
    }

    public boolean isMessageSent(String message) {
        return customWait.isElementPresent(By.xpath(String.format(infoSentMessage, message)));
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(tigerBonzoHeader);
    }
}
