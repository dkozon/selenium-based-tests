package net.kozon.selenium.niebezpiecznik.tests;

import net.kozon.selenium.common.tests.BaseTest;
import net.kozon.selenium.common.utils.PageObjectNiebezpiecznikManager;
import net.kozon.selenium.framework.tools.utils.UrlProvider;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserAgentTest extends BaseTest {

    private static final String MESSAGE_FOR_TIGER = "Mocium Panie! Łaskawie prosim o oszczędzenie nas!";
    private String url;
    private PageObjectNiebezpiecznikManager manager;

    @BeforeClass
    private void startUp() {
        manager = new PageObjectNiebezpiecznikManager(webDriver);
        url = UrlProvider.NIEBEZPIECZNIK.getUrl();
    }

    @AfterClass
    private void tearDown() {
        webDriver.quit();
    }

    @Test(enabled = false)
    public void TigerBonzoTest() {
        manager.mainNiebezpiecznikPage().loadPage(url);
        Assertions.assertThat(manager.mainNiebezpiecznikPage().isLoaded()).isTrue();
        manager.mainNiebezpiecznikPage().clickUserAgentLink();
        Assertions.assertThat(manager.tigerBonzoHackerPage().isLoaded());
        manager.tigerBonzoHackerPage().fillFieldForQuestion(MESSAGE_FOR_TIGER)
                .clickChceckButton();
        Assertions.assertThat(manager.tigerBonzoHackerPage().isMessageSent(MESSAGE_FOR_TIGER)).isTrue();
    }
}
