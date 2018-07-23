package net.kozon.selenium.example.test.framework.internet.cucumber.StepDefinition;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.framework.tools.docker.EnvironmentOnDocker;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.assertj.core.api.Assertions;

public class FileUploadCucumberTest extends BaseTest{

    private String url;
    private String containerId;
    private PageObjectTheInternetManager manager;
    private EnvironmentOnDocker environmentOnDocker;

    private static final String NAME_OF_FILE_FOR_UPLOAD = "test.html";
    private static final String PATH_TO_RESOURCE_FOR_UPLOAD = String.format("src/test/resources/%s", NAME_OF_FILE_FOR_UPLOAD);

    @Before
    public void setUp () throws InterruptedException, DockerCertificateException, DockerException{
        manager = new PageObjectTheInternetManager(webDriver);
        environmentOnDocker = new EnvironmentOnDocker();
        containerId = environmentOnDocker.startDockerClient();
    }

    @Given("^I have to open File Upload page$")
    public void iHaveToOpenFileUploadPage()  {
        url = UrlProvider.DOCKER_INTERNET.getUrl();
    }

    @When("^I load file to the page$")
    public void iLoadFileToThePage() {
        manager.getMainPage().loadPage(url);
        Assertions.assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickUploadLink();
        Assertions.assertThat(manager.getFileUploadPage().isLoaded()).isTrue();
        manager.getFileUploadPage()
                .uploadFile(PATH_TO_RESOURCE_FOR_UPLOAD)
                .makeUploadButtonScaled()
                .clickUpload();
    }

    @Then("^I have confirmation that file is uploaded correctly$")
    public void iHaveConfirmationThatFileIsUploadedCorrectly() {
        Assertions.assertThat(manager.getFileUploadPage().isFileUploaded(NAME_OF_FILE_FOR_UPLOAD)).isTrue();
    }

    @After
    public void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
        Assertions.assertThat(environmentOnDocker.stopAndRemoveDockerClient(containerId)).isTrue();
    }
}
