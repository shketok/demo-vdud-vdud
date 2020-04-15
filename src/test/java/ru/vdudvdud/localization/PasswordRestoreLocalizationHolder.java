package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.AbstractLocalizationProvider;
import ru.vdudvdud.localization.providers.LocalizationProviderFactory;

public enum PasswordRestoreLocalizationHolder implements LocalizedValue {

    RESTORE_PASSWORD_RESTORE_FORM_INVALID_EMAIL_MESSAGE("password_restore.restore_form.invalid_email_message"),
    RESTORE_PASSWORD_RESTORE_FORM_DIFFERENT_PASSWORD_MESSAGE(
        "password_restore.restore_form.different_passwords_message"),
    RESTORE_PASSWORD_RESTORE_FORM_EMPTY_PASSWORD_MESSAGE("password_restore.restore_form.empty_passwords_message");

    private static final AbstractLocalizationProvider passwordRestoreLocalizationProvider = LocalizationProviderFactory
        .getProvider("password_restore.PasswordRestore");

    private final String key;

    PasswordRestoreLocalizationHolder(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return passwordRestoreLocalizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return passwordRestoreLocalizationProvider.getLocalization(key, locale);
    }
}
