package net.kozon.selenium.framework.tools.owasp;

import net.kozon.selenium.framework.tools.utils.Configuration;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


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
