package net.kozon.selenium.example.test.framework.common.owasp;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
abstract class ProxyStrategy {

    private static String HTTP_PROXY = "localhost:8080";
    private static String SSL_PROXY = "localhost:8080";
    abstract WebDriver webDriver() throws IOException;

    Proxy proxyConfig() {
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL)
                .setHttpProxy(HTTP_PROXY)
                .setSslProxy(SSL_PROXY);
        return proxy;
    }
}
