package ru.vdudvdud.adaptors.selenide.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.NotImplementedException;
import ru.vdudvdud.adaptors.selenide.Configuration;

import static ru.vdudvdud.adaptors.selenide.driver.DriverFactory.PAGE_CLOSE_TIMEOUT;

/**
 * Базовый класс для описания страницы приложения.
 */
public abstract class BasePage extends PageObject {

    /**
     * Проверка по элементу, что выполняются переданное условие.
     *
     * @param condition ожидаемое состояние элемента.
     * @return true - если найден элемент, false - в противном случае.
     */
    public boolean isPageInState(Condition condition) {
        return getMainElement().is(condition);
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

    /**
     * Стандартное время ожидание пока страница закроется
     * (необходимо в случае ожадания какой-либо загрузки или процесса на странице, а затем ее автоматического закрытия).
     * В случае необходимости может быть переопределен
     *
     * @return Вызвращает время ожидания
     */
    protected Long notExistTimeOut() {
        return PAGE_CLOSE_TIMEOUT;
    }

    /**
     * Метод ожидания пока страница закроется.
     * (необходимо в случае ожадания какой-либо загрузки или процесса на странице, а затем ее автоматического закрытия).
     */
    public void getPageCloseTimeout() {
        getMainElement().waitUntil(Condition.not(Condition.exist), notExistTimeOut());
    }

    public void setTextToTxbWithTextCheck(SelenideElement element, String text) {
        int attempts = 0;
        while (!element.getValue().equals(text) && attempts < Configuration.getInstance().getMaxRetryAttemptsNumber()) {
            element.setValue(text);
            attempts++;
        }
    }
}
