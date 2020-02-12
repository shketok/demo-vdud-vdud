package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.vdudvdud.adaptors.selenide.Configuration;
import ru.vdudvdud.adaptors.selenide.driver.DriverContainer;

public class BrowserUtils {
    private static final long PAGE_LOAD_WAIT = Configuration.getInstance().getTimeout("timeout.page_load.wait");

    public static void restartBrowser() {
        DriverContainer.quit();
        DriverContainer.setDrivers();
    }

    /**
     * Дождаться пока отработает jQuery
     */
    public static void waitForJquery() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), PAGE_LOAD_WAIT);
        wait.until((WebDriver dr) -> (boolean) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return jQuery.active == 0"));
    }

    /**
     * Дождаться пока отработает AJAX
     */
    public static void waitForAjax() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), PAGE_LOAD_WAIT);
        wait.until((WebDriver dr) -> ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Дождаться пока отработает jQuery и Дождаться пока отработает AJAX
     */
    public static void waitForJqueryAndAjax() {
        waitForJquery();
        waitForAjax();
    }
}
