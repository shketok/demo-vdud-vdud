package ru.vdudvdud.adaptors.selenide.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

import static java.lang.String.format;

/**
 * Класс-утилита для работы с JSON файлами.
 */
public class JSONUtils {

    private static final Logger LOG = Logger.getInstance();

    /**
     * Маппинг JSON объекта на экземпляр класса.
     *
     * @param filePath    путь к JSON файлу для чтения.
     * @param key         ключ для поиска объекта в JSON.
     * @param targetClass тип объекта, на которые будет мапится объект из JSON.
     * @param <T>         обобщенный тип объекта, на которые будет мапится объект из JSON.
     * @return объект, извлеченный из JSON.
     */
    public static <T> T mapToObject(String filePath, String key, Class<T> targetClass) {
        JSONObject jsonObject = (JSONObject) readFromFile(filePath).get(key);
        return new Gson().fromJson(jsonObject.toJSONString(), targetClass);
    }

    /**
     * Маппинг JSON объекта на экземпляр класса.
     *
     * @param json        JSON.
     * @param targetClass тип объекта, на которые будет мапится объект из JSON.
     * @param <T>         обобщенный тип объекта, на которые будет мапится объект из JSON.
     * @return объект, извлеченный из JSON.
     */
    public static <T> T mapToObject(JSONObject json, Class<T> targetClass) {
        return new Gson().fromJson(json.toJSONString(), targetClass);
    }

    /**
     * Маппинг JSON объекта на экземпляр класса.
     *
     * @param json JSON.
     * @param type тип объекта, на которые будет мапится объект из JSON.
     * @param <T>  обобщенный тип объекта, на которые будет мапится объект из JSON.
     * @return объект, извлеченный из JSON.
     */
    public static <T> T mapToObject(JSONObject json, Type type) {
        return new Gson().fromJson(json.toJSONString(), type);
    }

    /**
     * Получение массива строк из объекта JSON по ключу.
     *
     * @param filePath путь к JSON файлу для чтения.
     * @param key      ключ для полиска массива строк в объекте JSON.
     * @return массив строк, извлеченный из JSON объекта.
     */
    public static String[] getStringArray(String filePath, String key) {
        JSONArray jsonArray = (JSONArray) readFromFile(filePath).get(key);
        return new Gson().fromJson(jsonArray.toJSONString(), new TypeToken<String[]>() {
        }.getType());
    }

    /**
     * Получение массива строк из объекта JSON по ключу.
     *
     * @param jsonObject экземпляр JSONObject.
     * @param key        ключ для полиска массива строк в объекте JSON.
     * @return массив строк, извлеченный из JSON объекта.
     */
    public static String[] getStringArray(JSONObject jsonObject, String key) {
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return new Gson().fromJson(jsonArray.toJSONString(), new TypeToken<String[]>() {
        }.getType());
    }

    /**
     * Чтение JSON файла в объект JSONObject.
     *
     * @param filePath путь к файлу.
     * @return экземпляр JSONObject.
     */
    public static JSONObject readFromFile(String filePath) {
        JSONObject data = null;
        try {
            data = (JSONObject) new JSONParser().parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            LOG.fatal(String.format("Exception occurred during read data from file: %s", filePath), e);
        }
        return data;
    }

    /**
     * Запись в JSON файл.
     *
     * @param data     Данные для добавление в json.
     * @param filePath путь к файлу.
     */
    public static void writeToFile(Object data, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            LOG.fatal(String.format("Exception occurred during write data to file: %s", filePath), e);
        }
    }
}
