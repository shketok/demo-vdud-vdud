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

    /**
     * Основной элемент, от которого осуществляется поиск всех вложенных элементов страницы.
     */
    protected SelenideElement mainElement;

    /**
     * Конструктор основного элемента.
     * @param mainElement основной элемент страницы.
     */
    protected PageObject(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }


    /**
     * Длительное ожидание состояния элемента.
     * @param condition условие ожидания.
     * @return состояние, в котором находится элемент.
     */
    public boolean isElementInState(Condition condition) {
        return SmartWait.isElementInState(getMainElement(), condition);
    }

    /**
     * Проверка находится ли элемент в указанном состоянии. Если элемент не в указанном состоянии, то тест не пройдет.
     * @param condition условие ожидания.
     * @return Найденный элемент в указанном состоянии.
     */
    public SelenideElement shouldBe(Condition condition) {
        return getMainElement().shouldBe(condition);
    }

    /**
     * Проверка не находится ли элемент в указанном состоянии. Если элемент в указанном состоянии, то тест не пройдет.
     * @param condition условие ожидания.
     * @return Найденный элемент в указанном состоянии.
     */
    public SelenideElement shouldNotBe(Condition condition) {
        return getMainElement().shouldNotBe(condition);
    }

    /**
     * Получение флага, находится ли элемент в указанном состоянии в данный момент, получение состояния происходит без
     * ожиданий.
     * @param condition условие ожидания.
     * @return True - элемент в указанном состоянии, False - элемент не в указанном состоянии.
     */
    public boolean isMainElement(Condition condition) {
        return getMainElement().is(condition);
    }

    /**
     * Возвращает уникальный элемент, по которому можно идентифицировать PageObject.
     *
     * @return SelenideElement, найденный по заданному локатору.
     */
    protected SelenideElement getMainElement() {
        return mainElement;
    };
}
