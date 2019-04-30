package net.kozon.selenium.framework.tools.utils;

public enum UrlProvider {
    GOOGLE_MAIN("https://www.google.com/en"),
    THE_INTERNET("https://the-internet.herokuapp.com/"),
    DOCKER_INTERNET("http://localhost:5000"),
    NIEBEZPIECZNIK("http://192.168.88.253/");

    private final String baseUrl;

    UrlProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl;
    }
}
