package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PageObjectManager;
import utils.UrlProvider;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class GmailLoginTest {

    private WebDriver webDriver;
    private PageObjectManager manager;
    private String url;

    @BeforeMethod
    private void setUp() {
        webDriver = new FirefoxDriver();
        manager = new PageObjectManager(webDriver);
        url = UrlProvider.GOOGLE_MAIN.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test(dataProvider = "loginGmailData")
    public void shouldNotLoggedInTest(String login, String password) {
        manager.mainPage()
                .loadPage(url);
        assertThat(manager.mainPage().isLoaded()).isTrue();
        manager.mainPage()
                .signInButtonClick();
        assertThat(manager.loginPage().isLoaded()).isTrue();
        manager.loginPage()
                .enterMailAddress(login)
                .clickNextButton();
        assertThat(manager.passwordPage().isLoaded()).isTrue();
        assertThat(manager.passwordPage().getEmailDisplayed()).contains(login + "@gmail.com");
        assertThat(manager.passwordPage().isStaySignedInCheckboxSelected()).isTrue();
        manager.passwordPage()
                .enterPassword(password)
                .signInButtonClick();
    }

    @DataProvider(name = "loginGmailData")
    public Object[][] testData() {
        return new Object[][]{
                {"epam321", "testtest1"}
        };
    }
}
