package ru.vdudvdud.adaptors.selenide.base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Базовый класс для описания страницы приложения.
 */
public abstract class BasePage extends PageObject {

    /**
     * Конструктор основного элемента.
     *
     * @param mainElement основной элемент страницы.
     */
    protected BasePage(SelenideElement mainElement) {
        super(mainElement);
    }

    /**
     * Открытие страница по URL.
     */
    public void open() {
        String url = getPageUrl();
        LOG.info("Navigate to URL: " + url);
        WebDriverRunner.getWebDriver().navigate().to(url);
    }

    /**
     * Получение URL страницы. Должен быть переопределен в каджом классе, описывающем конкретную страницу приложения.
     *
     * @return URL страницы.
     */
    protected String getPageUrl() {
        throw new NotImplementedException("Method doesn't implemented");
    }

    public void scrollToMainElement(boolean alignToTop) {
        getMainElement().scrollIntoView(alignToTop);
    }
}
