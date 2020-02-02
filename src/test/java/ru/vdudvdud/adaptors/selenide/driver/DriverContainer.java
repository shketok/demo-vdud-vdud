package ru.vdudvdud.adaptors.selenide.driver;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.vdudvdud.adaptors.selenide.utils.Logger;

import java.util.HashMap;

/**
 * Класс-контейнер, содержащий экземпляры веб-драйвера, готовые к использованию.
 */
public class DriverContainer {

    private static Instance currentInstance;

    private final static ThreadLocal<HashMap<Instance, RemoteWebDriver>> drivers = new InheritableThreadLocal<>() {
        @Override
        protected HashMap<Instance, RemoteWebDriver> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * Инициализация основного экземпляра драйвера.
     */
    public static void setDrivers() {
        currentInstance = Instance.FIRST;
        createDriver(Instance.FIRST);
        switchToFirst();
    }

    /**
     * Создание драйвера и помещение его в контейнер.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void createDriver(Instance instanceKey) {
        IDriverFactory driverFactory = new DriverFactory();
        RemoteWebDriver driver = driverFactory.getDriver();
        Logger.getInstance().info("Browser size is " + driver.manage().window().getSize().toString());
        drivers.get().put(instanceKey, driver);
    }
    /**
     * Получение драйвера и помещение его в контейнер.
     */
    public static RemoteWebDriver getDriver() {
        return drivers.get().get(currentInstance);
    }

    /**
     * Переключение на основной экземпляр драйвера.
     */
    public static void switchToFirst() {
        switchDriver(Instance.FIRST);
    }

    /**
     * Переключение на второй экземпляр драйвера.
     */
    public static void switchToSecond() {
        switchDriver(Instance.SECOND);
    }

    /**
     * Переключение на третий экземпляр драйвера.
     */
    public static void switchToThird() {
        switchDriver(Instance.THIRD);
    }

    /**
     * Переключение на третий экземпляр драйвера.
     */
    public static void switchToFourth() {
        switchDriver(Instance.FOURTH);
    }

    /**
     * Переключение на определенный экземпляр драйвера.
     * Если драйвер с заданным ключом отсутствует в контейнере, то происходит его создание.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void switchDriver(Instance instanceKey) {
        if (drivers.get().get(instanceKey) == null) {
            createDriver(instanceKey);
        }
        currentInstance = instanceKey;
        WebDriverRunner.setWebDriver(drivers.get().get(instanceKey));
    }

    public static RemoteWebDriver getCurrentDriver() {
        return drivers.get().get(currentInstance);
    }

    /**
     * Уничтожение всех созданных экземпляров драйверов и очищение контейнера.
     */
    public static void quit() {
        for (Instance instanceKey : drivers.get().keySet()) {
            drivers.get().get(instanceKey).quit();
        }
        drivers.get().clear();
    }

    /**
     * Ключи, идентифицирующие экземпляры драйверов.
     */
    public enum Instance {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
