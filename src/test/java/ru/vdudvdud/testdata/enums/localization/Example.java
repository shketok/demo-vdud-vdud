package ru.vdudvdud.testdata.enums.localization;

import ru.vdudvdud.adaptors.selenide.Localization;

public enum Example implements ILocalizedValue {
    VALUE("page.space.constant_name");

    private String propKey;

    Example(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getValue() {
        return Localization.getInstance().getValue(this.propKey);
    }
}