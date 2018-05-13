package net.kozon.selenium.example.test.framework.internet.pages;

import net.kozon.selenium.example.test.framework.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dariusz_Kozon on 19-May-17.
 */
public final class DragAndDropPage extends BasePage<DragAndDropPage> {

    @FindBy (xpath = "//div[@id='column-a']")
    private WebElement boxA;

    @FindBy (xpath = "//div[@id='column-b']")
    private WebElement boxB;

    @FindBy (xpath = "//h3[contains(text(), 'Drag and Drop')]")
    private WebElement dragAndDropPageHeader;

    @Override
    protected DragAndDropPage getThis() {
        return this;
    }

    public DragAndDropPage(WebDriver webDriver) {
        super(webDriver);
    }

    public DragAndDropPage dragElementToDrop() {
        Actions builder = new Actions(webDriver);
        Action dragAndDrop = builder.clickAndHold(boxA)
                .moveToElement(boxB)
                .release(boxB)
                .build();
        dragAndDrop.perform();
        return getThis();
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(dragAndDropPageHeader);
    }
}
