package net.kozon.selenium.example.test.framework.common.owasp;

import net.kozon.selenium.example.test.framework.common.utils.Configuration;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Dariusz_Kozon on 06.11.2017.
 */
abstract class ProxyStrategy {

    private static String HTTP_PROXY = Configuration.getPropertyFromFile("proxy");
    private static String SSL_PROXY = Configuration.getPropertyFromFile("proxy");
    abstract WebDriver webDriver() throws IOException;

    Proxy proxyConfig() {
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL)
                .setHttpProxy(HTTP_PROXY)
                .setSslProxy(SSL_PROXY);
        return proxy;
    }
}
