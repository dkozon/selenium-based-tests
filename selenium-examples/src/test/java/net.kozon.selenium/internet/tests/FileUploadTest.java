package net.kozon.selenium.internet.tests;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import net.kozon.selenium.common.tests.BaseTest;
import net.kozon.selenium.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.framework.tools.docker.EnvironmentOnDocker;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public final class FileUploadTest extends BaseTest {

    private static final String NAME_OF_FILE_FOR_UPLOAD = "test.html";
    private static final String PATH_TO_RESOURCE_FOR_UPLOAD = String.format("src/test/resources/%s", NAME_OF_FILE_FOR_UPLOAD);
    private String url;
    private String containerId;
    private PageObjectTheInternetManager manager;
    private EnvironmentOnDocker environmentOnDocker;

    public FileUploadTest() {
        manager = new PageObjectTheInternetManager(webDriver);
//        environmentOnDocker = new EnvironmentOnDocker();
    }

    @BeforeMethod
    private void startUp() throws InterruptedException, DockerCertificateException, DockerException {
//        containerId = environmentOnDocker.startDockerClient();
        url = UrlProvider.THE_INTERNET.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
   //     tearDownGridIfNeeded();
 //       Assertions.assertThat(environmentOnDocker.stopAndRemoveDockerClient(containerId)).isTrue();
    }

    @Test
    public void shouldUploadFile() {
        manager.getMainPage().loadPage(url);
        Assertions.assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickUploadLink();
        Assertions.assertThat(manager.getFileUploadPage().isLoaded()).isTrue();
        manager.getFileUploadPage()
                .uploadFile(PATH_TO_RESOURCE_FOR_UPLOAD)
                .makeUploadButtonScaled()
                .clickUpload();
        Assertions.assertThat(manager.getFileUploadPage().isFileUploaded(NAME_OF_FILE_FOR_UPLOAD)).isTrue();
    }
}
