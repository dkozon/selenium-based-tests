package net.kozon.selenium.common.utils;

import net.kozon.selenium.google.pages.LoginGooglePage;
import net.kozon.selenium.google.pages.MainGooglePage;
import net.kozon.selenium.google.pages.PasswordGooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectGoogleManager {

    private final WebDriver webDriver;
    private MainGooglePage mainGooglePage;
    private LoginGooglePage loginGooglePage;
    private PasswordGooglePage passwordGooglePage;

    public PageObjectGoogleManager(WebDriver webDriver) {
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
