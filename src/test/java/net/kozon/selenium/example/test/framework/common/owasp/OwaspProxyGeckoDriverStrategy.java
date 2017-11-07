package net.kozon.selenium.example.test.framework.common.owasp;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
public class OwaspProxyGeckoDriverStrategy implements ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        WebDriver webDriver = new FirefoxDriver(owaspZAPService(), owaspZAPOptions());
        return webDriver;
    }

    private FirefoxOptions owaspZAPOptions() {
        JsonObject json = new JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", PROXY);
        json.addProperty("httpProxyPort", PORT);
        json.addProperty("sslProxy", PROXY);
        json.addProperty("sslProxyPort", PORT);

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("proxy", json);
        return options;
    }

    private GeckoDriverService owaspZAPService() throws IOException {
        GeckoDriverService service = new GeckoDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/geckodriver.exe"))
                .usingAnyFreePort()
                .usingAnyFreePort()
                .build();
        service.start();
        return service;
    }
}
