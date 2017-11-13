package net.kozon.selenium.example.test.framework.common.owasp;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
public interface ProxyStrategy {
    String PROXY = "localhost";
    int PORT = 8081;
    WebDriver webDriver() throws IOException;
}
