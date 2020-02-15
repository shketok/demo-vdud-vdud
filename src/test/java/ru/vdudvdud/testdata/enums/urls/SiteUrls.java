package ru.vdudvdud.testdata.enums.urls;

import ru.vdudvdud.testdata.enums.urls.impl.IUrls;
import ru.vdudvdud.testdata.enums.urls.impl.IUrlsImpl;

public enum SiteUrls implements IUrls {
    RETURN_CONDITIONS("site/usloviya-vozvrata/"),
    FAQ("site/faq/"),
    PRIVACY("site/privacy/"),
    OFFER("site/oferta/");

    private final IUrls implementation;

    SiteUrls(String urlPart) {
        implementation = new IUrlsImpl(urlPart);
    }

    @Override
    public String getHomeUrl() {
        return implementation.getHomeUrl();
    }

    @Override
    public String getUrlPart() {
        return implementation.getUrlPart();
    }

    @Override
    public void navigate() {
        implementation.navigate();
    }

}
