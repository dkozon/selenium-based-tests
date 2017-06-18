package net.kozon.selenium.example.test.framework.common.utils;

/**
 * Created by Dariusz_Kozon on 16-Jan-17.
 */
public enum UrlProvider {
    GOOGLE_MAIN("https://www.google.com/en"),
    THE_INTERNET("https://the-internet.herokuapp.com/");

    private final String baseUrl;

    UrlProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl;
    }
}
