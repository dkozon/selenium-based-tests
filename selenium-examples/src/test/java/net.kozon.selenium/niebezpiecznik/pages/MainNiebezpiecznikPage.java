package net.kozon.selenium.niebezpiecznik.pages;

import net.kozon.selenium.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainNiebezpiecznikPage extends BasePage<MainNiebezpiecznikPage> {

    @FindBy(xpath = "/html/body/div/div[3]/table/tbody/tr[2]/td[4]/p")
    private WebElement userAgentHeader;

    @FindBy(xpath = "/html/body/div/div[3]/table/tbody/tr[2]/td[4]/a[2]")
    private WebElement userAgentLink;

    public MainNiebezpiecznikPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected MainNiebezpiecznikPage getThis() {
        return this;
    }

    public MainNiebezpiecznikPage clickUserAgentLink() {
        customWait.clickElement(userAgentLink);
        return getThis();
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(userAgentHeader);
    }
}
