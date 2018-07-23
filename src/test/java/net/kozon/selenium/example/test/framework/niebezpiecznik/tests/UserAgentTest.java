package net.kozon.selenium.example.test.framework.niebezpiecznik.tests;

import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectNiebezpiecznikManager;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAgentTest extends BaseTest {

    private static final String MESSAGE_FOR_TIGER = "Mocium Panie! Łaskawie prosim o oszczędzenie nas!";
    private String url;
    private PageObjectNiebezpiecznikManager manager;

    public UserAgentTest() {
        manager = new PageObjectNiebezpiecznikManager(webDriver);
    }

    @BeforeMethod
    private void startUp() {
        url = UrlProvider.NIEBEZPIECZNIK.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
    }

    @Test
    public void test() {
        manager.mainNiebezpiecznikPage().loadPage(url);
        Assertions.assertThat(manager.mainNiebezpiecznikPage().isLoaded()).isTrue();
        manager.mainNiebezpiecznikPage().clickUserAgentLink();
        Assertions.assertThat(manager.tigerBonzoHackerPage().isLoaded());
        manager.tigerBonzoHackerPage().fillFieldForQuestion(MESSAGE_FOR_TIGER)
                .clickChceckButton();
        Assertions.assertThat(manager.tigerBonzoHackerPage().isMessageSent(MESSAGE_FOR_TIGER)).isTrue();
    }
}
