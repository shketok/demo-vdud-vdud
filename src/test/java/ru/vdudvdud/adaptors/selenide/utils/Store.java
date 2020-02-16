package ru.vdudvdud.adaptors.selenide.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;

import static java.lang.String.format;

/**
 * Класс-синглтон, позволяющий хранить пары ключ-значение для передачи данных между различными
 * шагами теста, либо тестами. Для хранения данных используется механизм сериализации объекта в JSON и запись в файл.
 * Кроме того, временный директорий используется для хранения временных файлов, необходимых для тестов.
 */
public class Store {

    private static ThreadLocal<Store> instance = new InheritableThreadLocal<>();

    private static final Logger LOG = Logger.getInstance();

    private String storePath;

    /**
     * Приватный конструктор, инициализируюзщий стор.
     */
    private Store() {
        storePath = Paths.get(FileManager.getAbsolutePath("store"), String.valueOf(Thread.currentThread().getId())).toString();
        FileManager.createDirectory(storePath);
    }

    /**
     * Метод, возвращающий единственный экземпляр класса. Если экземпляр не создан - создает новый.
     *
     * @return экземпляр класса.
     */
    public static Store getInstance() {
        if (instance.get() == null) {
            instance.set(new Store());
        }
        return instance.get();
    }

    /**
     * Метод, возвращающий путь к временному директорию, который используется стором.
     *
     * @return путь к директорию.
     */
    public String getStorePath() {
        return storePath;
    }

    /**
     * Метод, добавляющий пару ключ-значение в стор и сериализующий объект.
     *
     * @param object объект, который необходимо сохранить в сторе.
     * @param name   ключ, по которому объект будет доступен в сторе.
     * @param <T>    тип объекта, которые будет сохранен в сторе.
     */
    public <T> void store(T object, String name) {
        try (Writer writer = new FileWriter(Paths.get(storePath, format("%s.json", name)).toString())) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(object, writer);
        } catch (IOException e) {
            LOG.fatal(format("IO exception during store operation\n%s", e.getMessage()), e);
        }
    }

    /**
     * Метод, позволяющий получить данные из стора.
     *
     * @param name   ключ, по которому необходимо получить объект из стора.
     * @param tClass экземпляр класса, объект которого будет получен из стора.
     * @param <T>    тип объекта, который будет получен из стора.
     * @return значение, хранящееся в сторе.
     */
    public <T> T get(String name, Class<T> tClass) {
        try (Reader reader = new FileReader(Paths.get(storePath, format("%s.json", name)).toString())) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, tClass);
        } catch (IOException e) {
            LOG.fatal(format("IO exception during store operation\n%s", e.getMessage()), e);
        }
        return null;
    }

    /**
     * Метод, очищающий стор. Рекурсивно удаляет файлы и директорию, в которой хранятся объекты.
     */
    public void clean() {
        FileManager.deleteFolderWithFiles(storePath);
    }
}
