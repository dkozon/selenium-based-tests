package net.kozon.selenium.example.test.framework.common.owasp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
public class OwaspProxyChromeDriverStrategy extends ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        return new ChromeDriver(owaspZAProxyService(), owaspZAProxyOptions());
    }

    private ChromeOptions owaspZAProxyOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("proxy", jsonConfiguration());
        return options;
    }

    private ChromeDriverService owaspZAProxyService() throws IOException {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/chromedriver234.exe"))
                .usingAnyFreePort()
                .usingAnyFreePort()
                .build();
        service.start();
        return service;
    }
}
