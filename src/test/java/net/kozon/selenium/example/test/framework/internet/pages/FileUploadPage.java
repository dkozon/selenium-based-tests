package net.kozon.selenium.example.test.framework.internet.pages;

import net.kozon.selenium.example.test.framework.common.pages.BasePage;
import net.kozon.selenium.framework.tools.utils.CustomWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public final class FileUploadPage extends BasePage<FileUploadPage> {

    private static Logger logger = LoggerFactory.getLogger(CustomWait.class);
    @FindBy(id = "file-upload")
    private WebElement fileUploadController;
    @FindBy(id = "file-submit")
    private WebElement uploadButton;
    private String fileUploadConfirmation = "//div[@id='uploaded-files' and contains(text(), '%s')]";

    public FileUploadPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected FileUploadPage getThis() {
        return this;
    }

    public FileUploadPage uploadFile(String filePath) {
        //uploading files using sendKeys() in edge does not work:
        //https://stackoverflow.com/questions/38749050/edge-upload-file-control-using-selenium
        File f = new File(filePath);
        logger.info(f.getAbsoluteFile().toString());
        fileUploadController.sendKeys(f.getAbsolutePath());
        return getThis();
    }

    public FileUploadPage clickUpload() {
        customWait.clickElement(uploadButton);
        return getThis();
    }

    public FileUploadPage makeUploadButtonScaled() {
        makeElementScaled(uploadButton);
        return getThis();
    }

    public boolean isFileUploaded(String fileName) {
        //example with element Changed in DOM after upload (element is not existing until DOM reload)
        return customWait.isElementPresent(By.xpath(String.format(fileUploadConfirmation, fileName)));
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(fileUploadController);
    }
}
