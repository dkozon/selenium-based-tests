package net.kozon.selenium.example.test.framework.common.owasp;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
abstract class ProxyStrategy {

    private static String PROXY = "localhost";
    private static int PORT = 8081;
    abstract WebDriver webDriver() throws IOException;

    JsonObject jsonConfiguration() {
        JsonObject json = new JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", PROXY);
        json.addProperty("httpProxyPort", PORT);
        json.addProperty("sslProxy", PROXY);
        json.addProperty("sslProxyPort", PORT);

        return json;
    }
}
