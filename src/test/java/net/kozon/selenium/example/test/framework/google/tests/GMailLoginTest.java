package net.kozon.selenium.example.test.framework.google.tests;

import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectGoogleManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class GMailLoginTest extends BaseTest {

    private String url;
    private PageObjectGoogleManager manager;

    public GMailLoginTest() {
        manager = new PageObjectGoogleManager(webDriver);
    }

    @BeforeMethod
    private void startUp() {
        url = UrlProvider.GOOGLE_MAIN.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
    }

    @Test(dataProvider = "loginGMailData")
    public void shouldLoggedInTest(String login, String password) {
        manager.mainPage()
                .loadPage(url);
        assertThat(manager.mainPage().isLoaded()).isTrue();
        manager.mainPage()
                .signInButtonClick();
        assertThat(manager.loginPage().isLoaded()).isTrue();
        manager.loginPage()
                .enterMailAddress(login)
                .alternateClickNextButton();
                //.clickNextButton();
        assertThat(manager.passwordPage().isLoaded()).isTrue();
        assertThat(manager.passwordPage().getEmailDisplayed()).contains(login + "@gmail.com");
        manager.passwordPage()
                .enterPassword(password)
                .nextButtonClick();
    }

    @DataProvider(name = "loginGMailData")
    public Object[][] testData() {
        return new Object[][]{
                {"epam321", "testtest1"}
        };
    }
}
