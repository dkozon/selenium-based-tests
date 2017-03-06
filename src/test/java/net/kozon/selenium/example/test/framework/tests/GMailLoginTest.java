package net.kozon.selenium.example.test.framework.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import net.kozon.selenium.example.test.framework.utils.UrlProvider;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class GMailLoginTest extends BaseTest {

    private String url;

    @BeforeMethod
    private void startUp() {
        url = UrlProvider.GOOGLE_MAIN.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
    }

    @Test(dataProvider = "loginGMailData")
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

    @DataProvider(name = "loginGMailData")
    public Object[][] testData() {
        return new Object[][]{
                {"epam321", "testtest1"}
        };
    }
}
