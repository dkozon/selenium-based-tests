package net.kozon.selenium.example.test.framework.google.tests;

import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectGoogleManager;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public final class GMailLoginTest extends BaseTest {

    private String url;
    private PageObjectGoogleManager manager;

    public GMailLoginTest() {
        manager = new PageObjectGoogleManager(webDriver);
    }

    @BeforeMethod
    private void startUp() {
        url = UrlProvider.GOOGLE_MAIN.getUrl();
    }

    @AfterClass
    private void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
    }

    //false positive example - wrong password and account has not been logged in but test has been marked as passed
    @Test(dataProvider = "loginGMailData")
    public void shouldLoggedInTest(String login, String password) {
        manager.mainPage()
                .loadPage(url);
        manager.mainPage()
                .signInButtonClick();
        manager.loginPage()
                .enterMailAddress(login)
                .alternateClickNextButton();
                //.clickNextButton();
        assertThat(manager.passwordPage().getEmailDisplayed()).contains(login + "@gmail.com");
        manager.passwordPage()
                .enterPassword(password)
                .nextButtonClick();
    }

    @DataProvider(name = "loginGMailData")
    public Object[][] testData() {
        return new Object[][]{
                {"epam321", "testtest1"},
                {"epam321", "testtest1"} //simulate more than one test data set
        };
    }
}
