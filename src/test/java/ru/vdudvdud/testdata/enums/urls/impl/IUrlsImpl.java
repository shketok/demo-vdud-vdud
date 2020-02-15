package ru.vdudvdud.testdata.enums.urls.impl;

import com.codeborne.selenide.WebDriverRunner;
import ru.vdudvdud.adaptors.selenide.Configuration;

public class IUrlsImpl implements IUrls {

    protected String urlPart;
    protected Configuration CONFIG = Configuration.getInstance();

    public IUrlsImpl(String urlPart) {
        this.urlPart = urlPart;
    }

    public String getHomeUrl() {
        return CONFIG.getProperty("url.home") + urlPart;
    }

    public String getUrlPart() {
        return urlPart;
    }

    public void navigate() {
        WebDriverRunner.getWebDriver().navigate().to(getHomeUrl());
    }
}
