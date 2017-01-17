package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pagepobjects.LoginPage;
import pagepobjects.MainPage;
import pagepobjects.PasswordPage;

/**
 * Created by Dariusz_Kozon on 12-Jan-17.
 */
public class PageObjectFactory {

    private final WebDriver webDriver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PasswordPage passwordPage;

    public PageObjectFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = PageFactory.initElements(webDriver, MainPage.class);
        }
        return mainPage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        }
        return loginPage;
    }

    public PasswordPage passwordPage() {
        if (passwordPage == null) {
            passwordPage = PageFactory.initElements(webDriver, PasswordPage.class);
        }
        return passwordPage;
    }
}
