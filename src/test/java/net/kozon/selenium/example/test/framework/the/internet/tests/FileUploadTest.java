package net.kozon.selenium.example.test.framework.the.internet.tests;

import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public class FileUploadTest extends BaseTest {

    private String url;
    private PageObjectTheInternetManager manager;

    public FileUploadTest() {
        manager = new PageObjectTheInternetManager(webDriver);
    }

    @BeforeMethod
    private void startUp() {
        url = UrlProvider.THE_INTERNET.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
    }

    @Test
    public void shouldUploadFile() {
        manager.theInternetMainPage().loadPage(url);
        assertThat(manager.theInternetMainPage().isLoaded()).isTrue();
        manager.theInternetMainPage().clickUploadLink();
    }
}
