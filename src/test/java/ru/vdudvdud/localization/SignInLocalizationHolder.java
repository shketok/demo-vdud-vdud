package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.AbstractLocalizationProvider;
import ru.vdudvdud.localization.providers.LocalizationProviderFactory;

public enum SignInLocalizationHolder implements LocalizedValue {
    SIGN_IN_LOGIN_IS_MANDATORY_MESSAGE("sign_in.login_is_mandatory_message"),
    SIGN_IN_PASSWORD_IS_MANDATORY_MESSAGE("sign_in.password_is_mandatory_message"),
    SIGN_IN_EITHER_LOGIN_OR_PASSWORD_IS_INCORRECT("sign_in.either_login_or_password_is_incorrect");

    private static final AbstractLocalizationProvider signInLocalizationProvider = LocalizationProviderFactory
        .getProvider("sign_in.SignIn");

    private final String key;

    SignInLocalizationHolder(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return signInLocalizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return signInLocalizationProvider.getLocalization(key, locale);
    }
}
