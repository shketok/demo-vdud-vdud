package ru.vdudvdud.testdata.enums.urls;

import ru.vdudvdud.testdata.enums.urls.impl.IUrls;
import ru.vdudvdud.testdata.enums.urls.impl.IUrlsImpl;

public enum MyUrls implements IUrls {
    MY_ORDERS("my/orders/"),
    MY_PROFILE("my/profile/");

    private final IUrls implementation;

    MyUrls(String urlPart) {
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
