package net.kozon.selenium.framework.tools.owasp;

public class ProxyStrategyFactory {
    private ProxyChromeDriverStrategy proxyChromeDriverStrategy;
    private ProxyEdgeDriverStrategy proxyEdgeDriverStrategy;
    private ProxyGeckoDriverStrategy proxyGeckoDriverStrategy;

    public ProxyChromeDriverStrategy getProxyChromeDriverStrategy() {
        if (proxyChromeDriverStrategy == null) {
            proxyChromeDriverStrategy = new ProxyChromeDriverStrategy();
        }
        return proxyChromeDriverStrategy;
    }

    public ProxyEdgeDriverStrategy getProxyEdgeDriverStrategy() {
        if (proxyEdgeDriverStrategy == null) {
            proxyEdgeDriverStrategy = new ProxyEdgeDriverStrategy();
        }
        return proxyEdgeDriverStrategy;
    }

    public ProxyGeckoDriverStrategy getProxyGeckoDriverStrategy() {
        if (proxyGeckoDriverStrategy == null) {
            proxyGeckoDriverStrategy = new ProxyGeckoDriverStrategy();
        }
        return proxyGeckoDriverStrategy;
    }
}
