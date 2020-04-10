package ru.vdudvdud.localization.providers;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

//TODO как будет времмя рассмотреть внутренности детальнее. Изучить работу бандлов и необходимость доп кэширования
public abstract class AbstractLocalizationProvider implements LocalizationProvider {

    protected static final Locale DEFAULT_LOCALE = new Locale("ru", "RU");
    private static final String LOCALIZATION_PATH_PREFIX = "localization";
    private static final String LOCALIZATION_PATH_DELIMETER = ".";

    /**
     * Используется для кэширования бандлов.
     */
    private final Map<Locale, ResourceBundle> bundles = new ConcurrentHashMap<>();

    /**
     * Путь до файла из пакета resources. Возможно следует передавать path
     */
    private final String filename;


    //todo path?
    public AbstractLocalizationProvider(@NonNull String filename) {
        this.filename = filename;
        bundles.put(DEFAULT_LOCALE, ResourceBundle
            .getBundle(StringUtils.joinWith(LOCALIZATION_PATH_DELIMETER, LOCALIZATION_PATH_PREFIX, filename),
                DEFAULT_LOCALE));
    }

    @Override
    public String getLocalization(@NonNull String key) {
        return getLocalization(key, DEFAULT_LOCALE);
    }

    @Override
    public String getLocalization(@NonNull String key, @NonNull Locale locale) {
        return bundles.computeIfAbsent(locale, loc -> ResourceBundle
            .getBundle(StringUtils.joinWith(LOCALIZATION_PATH_DELIMETER, LOCALIZATION_PATH_PREFIX, filename),
                loc)).getString(key);
    }
}
