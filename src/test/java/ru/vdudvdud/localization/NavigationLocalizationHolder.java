package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;
import ru.vdudvdud.localization.providers.AbstractLocalizationProvider;
import ru.vdudvdud.localization.providers.LocalizationProviderFactory;

public enum NavigationLocalizationHolder implements LocalizedValue {

    NEW("navigation.new"),
    T_SHIRTS("navigation.t_shirts"),
    BOMBERS("navigation.bombers"),
    CASES("navigation.cases"),
    PURSES("navigation.purses"),
    HATS("navigation.hats"),
    SWEATSHIRTS("navigation.sweatshirts"),
    PANTS("navigation.pants"),
    PLAIDS("navigation.plaids");

    private static final AbstractLocalizationProvider passwordRestoreLocalizationProvider = LocalizationProviderFactory
        .getProvider("navigation.Navigation");

    private final String key;

    NavigationLocalizationHolder(@NonNull String key) {
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
