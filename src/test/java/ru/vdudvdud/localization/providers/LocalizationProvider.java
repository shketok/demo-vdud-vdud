package ru.vdudvdud.localization.providers;

import java.util.Locale;
import lombok.NonNull;


public interface LocalizationProvider {

    /**
     * Получить локализацию для дефолтной локали
     * @param key
     * @return
     */
    String getLocalization(@NonNull String key);

    /**
     * Получить локализакцию по ключу для конкретной локали
     * @param key
     * @param locale
     * @return
     */
    String getLocalization(@NonNull String key, @NonNull Locale locale);

}
