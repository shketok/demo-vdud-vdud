package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.YopmailLocalizationProvider;

public enum YopmailLocalization implements LocalizedValue {
    EMAIL_SUBJECT_PASSWORD_RESTORE("email.subject.password_restore");

    private static final YopmailLocalizationProvider yopmail = new YopmailLocalizationProvider("yopmail.Yopmail");
    private final String key;

    YopmailLocalization(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return yopmail.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return yopmail.getLocalization(key, locale);
    }
}
