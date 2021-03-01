package net.kozon.selenium.internet.tests;

import net.kozon.selenium.common.tests.BaseTest;
import net.kozon.selenium.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class DragAndDropTest extends BaseTest {

    private String url;
    private PageObjectTheInternetManager manager;

    @BeforeClass
    private void startUp() {
        manager = new PageObjectTheInternetManager(webDriver);
        url = UrlProvider.THE_INTERNET.getUrl();
    }

    @AfterClass
    private void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
    }

    //TODO: does not work [https://github.com/SeleniumHQ/selenium/issues/1365]
    @Test(enabled = false)
    public void shouldDragBoxAAndDropInBoxBPlace() throws InterruptedException {
        manager.getMainPage().loadPage(url);
        assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickDragAndDropLink();
        assertThat(manager.getDragAndDropPage().isLoaded()).isTrue();
        manager.getDragAndDropPage().dragElementToDrop();
    }
}
