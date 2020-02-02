package ru.vdudvdud.testdata.enums;

import ru.vdudvdud.adaptors.selenide.Configuration;

public enum Urls {
    BASE("/"),
    SIGN_IN("login/"),
    SIGN_UP("signup/"),
    LOGOUT("?logout");

    private static final Configuration CONFIG = Configuration.getInstance();

    private String urlPart;

    Urls(String urlPart) {
        this.urlPart = urlPart;
    }

    public String getHomeUrl() {
        return CONFIG.getProperty("url.home") + urlPart;
    }

    public String getUrlPart() {
        return urlPart;
    }

}
