package net.kozon.selenium.framework.tools.owasp;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 07.11.2017.
 */
public class WebDriverContext {
    private ProxyStrategy strategy;

    public WebDriverContext setProxyStrategy(ProxyStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public WebDriver webDriver() throws IOException {
        return strategy.webDriver();
    }
}