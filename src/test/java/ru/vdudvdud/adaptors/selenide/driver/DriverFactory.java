package ru.vdudvdud.adaptors.selenide.driver;

import com.google.gson.reflect.TypeToken;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.utils.FileManager;
import ru.vdudvdud.adaptors.selenide.utils.JSONUtils;
import ru.vdudvdud.adaptors.selenide.utils.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static java.lang.String.format;

/**
 * Класс (фабрика), отвечающий за создание экземпляра веб-драйвера.
 */
public class DriverFactory implements IDriverFactory {

    private static final Logger LOG = Logger.getInstance();
    private static final Browser BROWSER = Browser.getValue(Configuration.getInstance().getProperty("browser"));
    public static final Long ELEMENT_TIMEOUT = Configuration.getInstance().getTimeout("timeout.element.wait");
    public static final Long PAGE_CLOSE_TIMEOUT = Configuration.getInstance().getTimeout("timeout.page.close");
    private static final Boolean REMOTE_WD = Boolean.valueOf(System.getenv("REMOTE_WD"));
    private static final String REMOTE_WD_URL = Configuration.getInstance().getProperty("browser.remote_wd.url");
    private static final String PLATFORM = System.getProperty("os.name").toLowerCase();

    /**
     * Получение экземпляра веб-драйвера, готового к использованию.
     *
     * @return экземпляр локального или удаленного драйвера.
     */
    @Override
    public RemoteWebDriver getDriver() {
        RemoteWebDriver driver;
        if (REMOTE_WD) {
            driver = getRemoteDriver();
        } else {
            driver = getLocalDriver();
        }
        com.codeborne.selenide.Configuration.timeout = ELEMENT_TIMEOUT;
        return driver;
    }

    /**
     * Создание локального веб-драйвера.
     *
     * @return экземпляр локального драйвера.
     */
    private RemoteWebDriver getLocalDriver() {
        RemoteWebDriver driver;
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = getChromeOptions();

                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException(format("Browser %s is not supported", BROWSER));
        }
        return driver;
    }

    /**
     * Создание удаленного веб-драйвера.
     *
     * @return экземпляр локального драйвера.
     */
    private RemoteWebDriver getRemoteDriver() {
        RemoteWebDriver driver;
        switch (BROWSER) {
            case CHROME:
                ChromeOptions chromeOptions = getChromeOptions();
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = getRemoteWebDriver(caps);
                break;
            default:
                throw new IllegalArgumentException(format("Browser %s is not supported", BROWSER));
        }
        return driver;
    }

    /**
     * Создание удаленного драйвера.
     *
     * @param caps Capabilities, описывающие драйвер, экземпляр которого необходимо создать.
     * @return экземпляр драйвера либо null, в случае некорректного URL.
     */
    private RemoteWebDriver getRemoteWebDriver(DesiredCapabilities caps) {
        try {
            return new RemoteWebDriver(new URL(REMOTE_WD_URL), caps);
        } catch (MalformedURLException e) {
            LOG.fatal("Unable to create Remote WebDriver", e);
            return null;
        }
    }

    /**
     * Создание экземпляра ChromeOptions, на основании файла driverOptions.json из ресурсов.
     *
     * @return ChromeOptions для инициализации драйвера.
     */
    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        String fileWithOptions = FileManager.getAbsolutePath(Configuration.getInstance().getProperty("browser.driver_options.file"));
        JSONObject options = (JSONObject) JSONUtils.readFromFile(fileWithOptions).get(Browser.CHROME.getBrowser());
        options = (JSONObject) options.get(getPlatformName());

        String[] args = JSONUtils.getStringArray(options, "args");
        chromeOptions.addArguments(args);

        JSONObject experimentalOptions = (JSONObject) options.get("experimentalOptions");
        String[] excludeSwitches = JSONUtils.getStringArray(experimentalOptions, "excludeSwitches");
        chromeOptions.setExperimentalOption("excludeSwitches", excludeSwitches);
        chromeOptions.setExperimentalOption("prefs", JSONUtils.mapToObject((JSONObject) experimentalOptions.get("prefs"), new TypeToken<HashMap<String, Object>>() {
        }.getType()));

        return chromeOptions;
    }

    /**
     * Получение имени платформы, на которой выполняются тесты.
     *
     * @return имя платформы.
     */
    private String getPlatformName() {
        String platform;
        if (PLATFORM.contains("win")) {
            platform = "windows";
        } else if (PLATFORM.contains("linux")) {
            platform = "linux";
        } else {
            throw new IllegalArgumentException(format("Platform %s is not supported", PLATFORM));
        }
        return platform;
    }

}
