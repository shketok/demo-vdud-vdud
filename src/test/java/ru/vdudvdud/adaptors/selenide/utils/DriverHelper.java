package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.vdudvdud.adaptors.selenide.Configuration;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;
import static java.lang.String.format;

/**
 * Класс, содержащий методы-утилиты для работы с веб-драйвером.
 */
public class DriverHelper {

    private static final Long NEW_TAB_TIMEOUT = Configuration.getInstance().getTimeout("timeout.new_tab");
    public static final Long ELEMENT_TIMEOUT = Configuration.getInstance().getTimeout("timeout.element.wait");

    private DriverHelper() {
    }

    /**
     * Переключение на новую открывшуюся вкладку браузера и закрытие старой вкладки.
     */
    public static void switchToNewWindowAndCloseOld() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String currentHandle = driver.getWindowHandle();
        new FluentWait<>(driver).withTimeout(Duration.ofMillis(NEW_TAB_TIMEOUT)).until(d -> d.getWindowHandles().size() > 1);

        Set<String> windowHandles = driver.getWindowHandles();
        Optional<String> newWindowHandle = windowHandles.stream().filter(h -> !h.equals(currentHandle)).findFirst();
        driver.close();
        newWindowHandle.ifPresent(s -> Selenide.switchTo().window(s));
    }

    /**
     * Нажатие по элементу с помощью JavaScript.
     *
     * @param element элемент, по которому необходимо произвести нажатие.
     */
    public static void clickJS(SelenideElement element) {
        Selenide.executeJavaScript("$(arguments[0]).click();", element);
    }

    /**
     * сделать скроиншот и добавить его в Allure
     *
     * @return
     */
    @Attachment(type = "image/png")
    public static byte[] makeScreenshot() {
        File file = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        Logger.getInstance().info(format("Screenshot path is: %s", file.getPath()));
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Дождаться пока отработает AJAX и jQuery
     */
    public static void waitForPageToLoad() {
        waitForJquery();
        waitForAjax();
    }

    /**
     * Дождаться пока отработает jQuery
     */
    public static void waitForJquery() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 30);
        wait.until((WebDriver dr) -> (boolean) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return jQuery.active == 0"));
    }

    /**
     * Дождаться пока отработает AJAX
     */
    public static void waitForAjax() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 30);
        wait.until((WebDriver dr) -> ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Пошевелить мышку, чтобы появились элементы.
     */
    public static void moveMouse() {
        actions().moveByOffset(1, 0).moveByOffset(-1, 0).build().perform();
    }

    /**
     * Вставка текста из буфера.
     */
    public static void pasteFromClipboard() {
        actions().sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();
    }

    /**
     * Получение данных из буфера обмена.
     */
    public static String getClipboardData() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String result = "";
        try {
            Thread.sleep(1000);
            result = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Ожидание элемента.
     */
    public static boolean isVisibleWithWait(SelenideElement element) {
        try {
            element.shouldBe(visible);
        } catch (ElementNotFound ex) {
            return false;
        }
        return true;
    }

    /**
     * Функция прокрутки до элемента.
     *
     * @param scrollableElement элемент, который будет прокручиваться.
     * @param scrollToElement   элемент, до которого нужно прокрутить
     */
    public static void smartScrollIntoView(SelenideElement scrollableElement, SelenideElement scrollToElement) {
        scrollableElement.shouldBe(visible);
        Selenide.executeJavaScript("var evt = document.createEvent('MouseEvents');\n" +
                        "evt.initEvent('wheel', true, true);\n" +
                        "evt.deltaY = -20;\n" +
                        "arguments[0].style.top = arguments[0].getBoundingClientRect().top - arguments[1].getBoundingClientRect().top + 'px';\n" +
                        "arguments[0].dispatchEvent(evt);",
                scrollableElement.toWebElement(), scrollToElement.toWebElement());


    }

    /**
     * Функция клика и прокрутки до элемента.
     *
     * @param elementToClick элемент, по которому требуется кликнуть.
     */
    public static void clickViaJs(SelenideElement elementToClick) {
        com.codeborne.selenide.Configuration.clickViaJs = true;
        elementToClick.click();
        com.codeborne.selenide.Configuration.clickViaJs = false;

    }

    /**
     * Функция ожидание кликабельного элемента.
     *
     * @param elementToClick элемент, по которому требуется кликнуть.
     */
    public static void waitUntilElementClickable(SelenideElement elementToClick) {
        Wait<WebDriver> wait = new FluentWait<>(WebDriverRunner.getWebDriver()).withTimeout(Duration.ofMillis(Configuration.getInstance().getTimeout("timeout.element.wait")));
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick.toWebElement()));

    }
}
