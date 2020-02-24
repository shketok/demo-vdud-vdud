package ru.vdudvdud.testdata.enums.localization;

import ru.vdudvdud.adaptors.selenide.Localization;

public enum RestorePasswordLocalization implements ILocalizedValue {
    INCORRECT_EMAIL_MESSAGE("restore_password.restore_form.invalid_email_message");

    private String propKey;

    RestorePasswordLocalization(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getValue() {
        return Localization.getInstance().getValue(this.propKey);
    }
}
