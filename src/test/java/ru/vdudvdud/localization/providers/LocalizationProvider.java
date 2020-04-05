package ru.vdudvdud.localization.providers;

import java.util.Locale;
import lombok.NonNull;

//todo rename + javadoc
public interface LocalizationProvider {
    String getLocalization(@NonNull String key);

    String getLocalization(@NonNull String key, @NonNull Locale locale);

}
