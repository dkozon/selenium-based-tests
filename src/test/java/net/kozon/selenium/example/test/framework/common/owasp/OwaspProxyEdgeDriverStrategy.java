package net.kozon.selenium.example.test.framework.common.owasp;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
public class OwaspProxyEdgeDriverStrategy implements ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        WebDriver webDriver = new EdgeDriver(owaspZAPService(), owaspZAPOptions());
        return webDriver;
    }

    private EdgeOptions owaspZAPOptions() {
        JsonObject json = new JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", PROXY);
        json.addProperty("httpProxyPort", PORT);
        json.addProperty("sslProxy", PROXY);
        json.addProperty("sslProxyPort", PORT);

        EdgeOptions options = new EdgeOptions();
        options.setCapability("proxy", json);
        return options;
    }

    private EdgeDriverService owaspZAPService() throws IOException {
        EdgeDriverService service = new EdgeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/MicrosoftWebDriver16299.exe"))
                .usingAnyFreePort()
                .usingAnyFreePort()
                .build();
        service.start();
        return service;
    }
}
