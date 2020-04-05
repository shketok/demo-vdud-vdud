package ru.vdudvdud.localization;

import java.util.Locale;
import lombok.NonNull;

public interface LocalizedValue {

    String i18n();

    String i18n(@NonNull Locale locale);
}
