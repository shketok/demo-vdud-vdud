package ru.vdudvdud.testdata.enums.localization;

import ru.vdudvdud.adaptors.selenide.Localization;

public enum YopmailLocalization implements ILocalizedValue {

    EMAIL_SUBJECT_PASSWORD_RESTORE("email.subject.password_restore");

    private String propKey;

    YopmailLocalization(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getValue() {
        return Localization.getInstance().getLocalizedYopmailElement(propKey);
    }

}
