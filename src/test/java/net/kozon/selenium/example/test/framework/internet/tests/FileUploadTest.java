package net.kozon.selenium.example.test.framework.internet.tests;

import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public class FileUploadTest extends BaseTest {

    private String url;
    private PageObjectTheInternetManager manager;

    private static final String NAME_OF_FILE_FOR_UPLOAD = "test.html";
    private static final String PATH_TO_RESOURCE_FOR_UPLOAD = String.format("src/test/resources/%s", NAME_OF_FILE_FOR_UPLOAD);

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
    public void shouldUploadFile() throws URISyntaxException {
        manager.getMainPage().loadPage(url);
        Assertions.assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickUploadLink();
        Assertions.assertThat(manager.getFileUploadPage().isLoaded()).isTrue();
        manager.getFileUploadPage()
                .uploadFile(PATH_TO_RESOURCE_FOR_UPLOAD)
                .clickUpload();
        Assertions.assertThat(manager.getFileUploadPage().isFileUploaded(NAME_OF_FILE_FOR_UPLOAD)).isTrue();
    }
}
