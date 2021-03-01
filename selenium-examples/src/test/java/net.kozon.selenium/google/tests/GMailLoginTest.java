package net.kozon.selenium.google.tests;

import net.kozon.selenium.common.tests.BaseTest;
import net.kozon.selenium.common.utils.PageObjectGoogleManager;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class GMailLoginTest extends BaseTest {

    private String url;
    private PageObjectGoogleManager manager;

    @BeforeClass
    private void startUp() {
        manager = new PageObjectGoogleManager(webDriver);
        url = UrlProvider.GOOGLE_MAIN.getUrl();
    }

    @AfterClass
    private void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
    }

    //false positive example - wrong password and account has not been logged in but test has been marked as passed
    @Test(dataProvider = "loginGMailData", enabled = false)
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
