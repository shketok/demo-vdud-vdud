package ru.vdudvdud.testdata.enums.localization;

import ru.vdudvdud.adaptors.selenide.Localization;

public enum SignInLocalization implements ILocalizedValue {
    MANDATORY_LOGIN_MESSAGE("sign_in.login_is_mandatory_message"),
    MANDATORY_PASSWORD_MESSAGE("sign_in.password_is_mandatory_message"),
    EITHER_LOGIN_OR_PASSWORD_IS_INCORRECT("sign_in.either_login_or_password_is_incorrect");


    private String propKey;

    SignInLocalization(String propKey) {
        this.propKey = propKey;
    }

    @Override
    public String getValue() {
        return Localization.getInstance().getLocalizedLabel(this.propKey);
    }
}
