package net.kozon.selenium.common.utils;

import net.kozon.selenium.internet.pages.DragAndDropPage;
import net.kozon.selenium.internet.pages.FileUploadPage;
import net.kozon.selenium.internet.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectTheInternetManager {

    private final WebDriver webDriver;
    private MainPage mainPage;
    private FileUploadPage fileUploadPage;
    private DragAndDropPage dragAndDropPage;

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

    public DragAndDropPage getDragAndDropPage() {
        if (dragAndDropPage == null) {
            dragAndDropPage = PageFactory.initElements(webDriver, DragAndDropPage.class);
        }
        return dragAndDropPage;
    }
}