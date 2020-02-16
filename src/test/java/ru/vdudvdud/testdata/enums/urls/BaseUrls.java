package ru.vdudvdud.testdata.enums.urls;

import ru.vdudvdud.testdata.enums.urls.impl.IUrls;
import ru.vdudvdud.testdata.enums.urls.impl.IUrlsImpl;

public enum BaseUrls implements IUrls {
    BASE("/"),
    SIGN_IN("login/"),
    SIGN_UP("signup/"),
    LOGOUT("?logout"),
    FORGOT_PASSWORD("forgotpassword/"),
    ORDER("order/"),
    DELIVERY("dostavka/"),
    PAYMENT("oplata/"),,
    CONTACTS("contacts/");

    private final IUrls implementation;

    BaseUrls(String urlPart) {
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
