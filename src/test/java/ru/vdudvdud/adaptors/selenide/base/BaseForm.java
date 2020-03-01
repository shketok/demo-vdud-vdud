package ru.vdudvdud.adaptors.selenide.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static ru.vdudvdud.adaptors.selenide.driver.DriverFactory.PAGE_CLOSE_TIMEOUT;

/**
 * Базовый класс для описания страницы приложения.
 */
public abstract class BaseForm extends PageObject {

    protected SelenideElement mainElement;

    protected BaseForm(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

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
     * Стандартное время ожидание пока страница закроется
     * (необходимо в случае ожадания какой-либо загрузки или процесса на странице, а затем ее автоматического закрытия).
     * В случае необходимости может быть переопределен
     *
     * @return Вызвращает время ожидания
     */
    protected Long getPageCloseTimeout() {
        return PAGE_CLOSE_TIMEOUT;
    }

    /**
     * Метод ожидания пока страница закроется.
     * (необходимо в случае ожадания какой-либо загрузки или процесса на странице, а затем ее автоматического закрытия).
     */
    public void waitForNotPresent() {
        getMainElement().waitUntil(Condition.not(Condition.exist), getPageCloseTimeout());
    }

}
