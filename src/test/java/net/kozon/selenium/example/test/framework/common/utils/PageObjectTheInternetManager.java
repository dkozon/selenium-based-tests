package net.kozon.selenium.example.test.framework.common.utils;

import net.kozon.selenium.example.test.framework.the.internet.pageObjects.FileUploadPage;
import net.kozon.selenium.example.test.framework.the.internet.pageObjects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public class PageObjectTheInternetManager {

    private final WebDriver webDriver;
    private MainPage mainPage;
    private FileUploadPage fileUploadPage;

    public PageObjectTheInternetManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = PageFactory.initElements(webDriver, MainPage.class);
        }
        return mainPage;
    }

    public FileUploadPage getFileUploadPage() {
        if (fileUploadPage == null) {
            fileUploadPage = PageFactory.initElements(webDriver, FileUploadPage.class);
        }
        return fileUploadPage;
    }
}
