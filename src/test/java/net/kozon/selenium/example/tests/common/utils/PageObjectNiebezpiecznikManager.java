package net.kozon.selenium.example.tests.common.utils;

import net.kozon.selenium.example.tests.niebezpiecznik.pages.MainNiebezpiecznikPage;
import net.kozon.selenium.example.tests.niebezpiecznik.pages.TigerBonzoHackerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectNiebezpiecznikManager {

    private final WebDriver webDriver;
    private MainNiebezpiecznikPage mainNiebezpiecznikPage;
    private TigerBonzoHackerPage tigerBonzoHackerPage;

    public PageObjectNiebezpiecznikManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainNiebezpiecznikPage mainNiebezpiecznikPage() {
        if (mainNiebezpiecznikPage == null) {
            mainNiebezpiecznikPage = PageFactory.initElements(webDriver, MainNiebezpiecznikPage.class);
        }
        return mainNiebezpiecznikPage;
    }

    public TigerBonzoHackerPage tigerBonzoHackerPage() {
        if (tigerBonzoHackerPage == null) {
            tigerBonzoHackerPage = PageFactory.initElements(webDriver, TigerBonzoHackerPage.class);
        }
        return tigerBonzoHackerPage;
    }
}
