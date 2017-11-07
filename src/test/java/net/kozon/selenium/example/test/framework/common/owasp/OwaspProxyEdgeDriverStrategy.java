package net.kozon.selenium.example.test.framework.common.owasp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
public class OwaspProxyEdgeDriverStrategy extends ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        return new EdgeDriver(owaspZAPService(), owaspZAPOptions());
    }

    private EdgeOptions owaspZAPOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("proxy", jsonConfiguration());
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
