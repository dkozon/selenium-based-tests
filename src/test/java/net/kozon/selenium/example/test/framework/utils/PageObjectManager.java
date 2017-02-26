package net.kozon.selenium.example.test.framework.utils;

import net.kozon.selenium.example.test.framework.pageObjects.MainGooglePage;
import net.kozon.selenium.example.test.framework.pageObjects.googleLogin.LoginGooglePage;
import net.kozon.selenium.example.test.framework.pageObjects.googleLogin.PasswordGooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class PageObjectManager {

    private final WebDriver webDriver;
    private MainGooglePage mainGooglePage;
    private LoginGooglePage loginGooglePage;
    private PasswordGooglePage passwordGooglePage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainGooglePage mainPage() {
        if (mainGooglePage == null) {
            mainGooglePage = PageFactory.initElements(webDriver, MainGooglePage.class);
        }
        return mainGooglePage;
    }

    public LoginGooglePage loginPage() {
        if (loginGooglePage == null) {
            loginGooglePage = PageFactory.initElements(webDriver, LoginGooglePage.class);
        }
        return loginGooglePage;
    }

    public PasswordGooglePage passwordPage() {
        if (passwordGooglePage == null) {
            passwordGooglePage = PageFactory.initElements(webDriver, PasswordGooglePage.class);
        }
        return passwordGooglePage;
    }
}
