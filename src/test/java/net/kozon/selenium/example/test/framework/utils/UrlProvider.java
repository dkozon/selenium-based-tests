package net.kozon.selenium.example.test.framework.utils;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public enum UrlProvider {

    GOOGLE_MAIN("https://www.google.com/");

    private final String baseUrl;

    private UrlProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl;
    }
}
