package ru.vdudvdud.testdata.enums.urls;

import ru.vdudvdud.testdata.enums.urls.impl.IUrls;
import ru.vdudvdud.testdata.enums.urls.impl.IUrlsImpl;

public enum CategoryUrls implements IUrls {
    HATS("category/shapki/"),
    T_SHIRTS("category/futbolki/"),
    JACKETS("category/bomber/"),
    PHONE_CASE("category/chekhly-dlya-telefonov/"),
    PLAID("category/pledy/"),
    SWEATSHIRT("category/sweatshirt/"),
    PANTS("category/pants/");

    private final IUrls implementation;

    CategoryUrls(String urlPart) {
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
