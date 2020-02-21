package ru.vdudvdud.testdata.enums.localization;

import ru.vdudvdud.adaptors.selenide.Localization;

public enum CartLocalization implements ILocalizedValue {
    PRODUCTS_COST("cart.confirmation_form.products_cost"),
    DELIVERY_COST("cart.confirmation_form.delivery_cost"),
    TOTAL_EXCLUDE_DELIVERY("cart.confirmation_form.total_exclude_delivery");

    private String propKey;

    CartLocalization(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getValue() {
        return Localization.getInstance().getValue(this.propKey);
    }
}