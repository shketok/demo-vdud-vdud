package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.AbstractLocalizationProvider;
import ru.vdudvdud.localization.providers.LocalizationProviderFactory;

public enum CartLocalizationHolder implements LocalizedValue {

    CART_CONFIRMATION_FORM_PRODUCTS_COST("cart.confirmation_form.products_cost"),

    CART_CONFIRMATION_FORM_DELIVERY_COST("cart.confirmation_form.delivery_cost"),

    CART_CONFIRMATION_FORM_TOTAL_EXCLUDE_DELIVERY("cart.confirmation_form.total_exclude_delivery");

    private static final AbstractLocalizationProvider cartLocalizationProvider = LocalizationProviderFactory
        .getProvider("cart.Cart");
    private final String key;

    CartLocalizationHolder(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return cartLocalizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return cartLocalizationProvider.getLocalization(key, locale);
    }
}
