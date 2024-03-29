package ru.vdudvdud.adaptors.selenide;


import org.json.simple.JSONObject;
import ru.vdudvdud.adaptors.selenide.utils.JSONUtils;
import ru.vdudvdud.adaptors.selenide.utils.Logger;

import static java.lang.String.format;

/**
 * Класс-синглтон, отвечающий за получение данных из ресурсных файлов конфигурации в зависимости от локали.
 */
//TODO abstract class for all localizations //refactor
public class Localization {

    private static final String LABELS_LOC_FILE = "labels.json";
    private static final String YOPMAIL_LOC_FILE = "yopmail.json";
    private static Localization instance = null;

    private JSONObject localizedLabels;
    private JSONObject localizedYopmail;
    private Locale locale;

    private Localization() {
    }

    /**
     * Метод, возвращающий единственный экземпляр класса. Если экземпляр не создан - создает новый.
     *
     * @return экземпляр класса.
     */
    public static synchronized Localization getInstance() {
        if (instance == null) {
            instance = new Localization();
        }
        return instance;
    }

    /**
     * Устанавливает значение локали и считывает значения из labels.json.
     *
     * @param locale локаль, которую необходимо установить.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        String labelsLocalizationFilepath = Configuration.getInstance()
            .getLocalizationFilePath(this.locale.getLocale(), LABELS_LOC_FILE);
        String yopmailLocalizationFilepath = Configuration.getInstance()
            .getLocalizationFilePath(this.locale.getLocale(), YOPMAIL_LOC_FILE);
        localizedLabels = JSONUtils.readFromFile(labelsLocalizationFilepath);
        localizedYopmail = JSONUtils.readFromFile(yopmailLocalizationFilepath);
    }

    public String getLocale() {
        return locale.getLocale();
    }

    /**
     * Получение локализованного строкового значения лэйбла по ключу.
     *
     * @param token ключ, по которому необходимо искать значение.
     * @return локализованное значение лэйбла. Если значение не найдено, возвращается null.
     */
    public String getLocalizedLabel(String token) {
        String value = (String) localizedLabels.get(token);
        if (value != null) {
            return value;
        } else {
            Logger.getInstance().error(String.format("Token '%s' was not found for locale '%s'", token, locale.getLocale()));
            return null;
        }
    }

    /**
     * Получение локализованного строкового значения элемента почты yopmail по ключу.
     *
     * @param token ключ, по которому необходимо искать значение.
     * @return локализованное значение лэйбла. Если значение не найдено, возвращается null.
     */
    public String getLocalizedYopmailElement(String token) {
        String value = (String) localizedYopmail.get(token);
        if (value != null) {
            return value;
        } else {
            // TODO other locale;
            Logger.getInstance().error(format("Token '%s' was not found for locale '%s'", token, locale.getLocale()));
            return null;
        }
    }

    /**
     * Перечисление, представляющее возможные значения локали.
     */
    public enum Locale {
        EN("en"),
        RU("ru");

        private String loc;

        Locale(String loc) {
            this.loc = loc;
        }

        /**
         * Получение строкового представления перечисления.
         *
         * @return строковое значение локали.
         */
        public String getLocale() {
            return this.loc;
        }
    }
}
