package ru.vdudvdud.adaptors.selenide.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.utils.Logger;
import ru.vdudvdud.adaptors.selenide.utils.SmartWait;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Базовый класс, описывающий любой PageObject.
 */
public abstract class PageObject {

    protected static final Logger LOG = Logger.getInstance();

    private static final String BODY_LOC = "body";

    public boolean isElementInState(Condition condition) {
        return SmartWait.isElementInState(getMainElement(), condition);
    }

    public SelenideElement shouldBe(Condition condition) {
        return getMainElement().shouldBe(condition);
    }

    public SelenideElement shouldNotBe(Condition condition) {
        return getMainElement().shouldNotBe(condition);
    }

    public boolean isMainElement(Condition condition) {
        return getMainElement().is(condition);
    }

    /**
     * Возвращает уникальный элемент, по которому можно идентифицировать PageObject.
     *
     * @return SelenideElement, найденный по заданному локатору.
     */
    protected abstract SelenideElement getMainElement();

    public void waitUntilPageMainElementsLoad() {
        $(BODY_LOC).shouldBe(visible);
    }
}
