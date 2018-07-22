package net.kozon.selenium.example.test.framework.common.owasp;

/**
 * Created by Dariusz_Kozon on 07.11.2017.
 */
public class ProxyStrategyFactory {
    private OwaspProxyChromeDriverStrategy owaspProxyChromeDriverStrategy;
    private OwaspProxyEdgeDriverStrategy owaspProxyEdgeDriverStrategy;
    private OwaspProxyGeckoDriverStrategy owaspProxyGeckoDriverStrategy;

    public OwaspProxyChromeDriverStrategy getOwaspProxyChromeDriverStrategy() {
        if (owaspProxyChromeDriverStrategy == null) {
            owaspProxyChromeDriverStrategy = new OwaspProxyChromeDriverStrategy();
        }
        return owaspProxyChromeDriverStrategy;
    }

    public OwaspProxyEdgeDriverStrategy getOwaspProxyEdgeDriverStrategy() {
        if (owaspProxyEdgeDriverStrategy == null) {
            owaspProxyEdgeDriverStrategy = new OwaspProxyEdgeDriverStrategy();
        }
        return owaspProxyEdgeDriverStrategy;
    }

    public OwaspProxyGeckoDriverStrategy getOwaspProxyGeckoDriverStrategy() {
        if (owaspProxyGeckoDriverStrategy == null) {
            owaspProxyGeckoDriverStrategy = new OwaspProxyGeckoDriverStrategy();
        }
        return owaspProxyGeckoDriverStrategy;
    }
}
