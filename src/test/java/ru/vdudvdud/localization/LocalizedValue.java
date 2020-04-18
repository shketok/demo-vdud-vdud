package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;

public interface LocalizedValue {

    /**
     * Локализованное значение в дефолтной локали
     * @return
     */
    String i18n();

    /**
     * Локализованное значение в определенной локали
     * @param locale
     * @return
     */
    String i18n(@NonNull Locale locale);
}
