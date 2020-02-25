package ru.vdudvdud.adaptors.selenide.base;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.vdudvdud.adaptors.selenide.Localization;
import ru.vdudvdud.adaptors.selenide.driver.DriverContainer;
import ru.vdudvdud.adaptors.selenide.utils.DriverHelper;
import ru.vdudvdud.adaptors.selenide.utils.Logger;
import ru.vdudvdud.adaptors.selenide.utils.Store;
import ru.vdudvdud.testdata.objects.Cart;

import static java.lang.String.format;

/**
 * Базовый класс, описывающий любой тест.
 */
public abstract class BaseTest {

    protected static final Logger LOG = Logger.getInstance();

    /**
     * Метод для хранения и запуска автоматизированного сценария.
     */
    public abstract void runTest();


    /**
     * Метод, выполняющийся один раз перед всеми тестами в сьюте.
     * Устанавливает локализацию.
     */
    @BeforeSuite
    public void beforeSuite() {
        Localization.getInstance().setLocale(Localization.Locale.RU);
    }


    /**
     * Метод, выполняюшщийся перед каждый тестом (@Test) в сьюте.
     * Инициализирует веб-драйвер, добавляет слушатель AllureSelenide с целью логирования действий Selenide'а, логирует имя теста.
     *
     * @param testContext текущий контекст теста.
     */
    @BeforeMethod
    public void beforeMethod(ITestContext testContext) {
        DriverContainer.setDrivers();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        LOG.info(format("Test '%s' started", testContext.getName()));
    }

    /**
     * Метод, выполняющийся после каждого теста (@Test) в сьюте.
     * Закрывает браузер, логирует имя выполнившегося теста.
     *
     * @param testContext текущий контекст теста.
     * @param testResult  результат выполнения теста.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext testContext, ITestResult testResult) {
        if (!testResult.isSuccess())
            DriverHelper.makeScreenshot();

        DriverContainer.quit();
        LOG.info(format("Test '%s' finished", testContext.getName()));

        Cart.getInstance().clean();
    }

    /**
     * Метод, выполняющийся один раз после завершения всех тестов в текущем сьюте.
     * Очищает стор.
     */
    @AfterSuite
    public void afterSuite() {
        Store.getInstance().clean();
    }
}
