package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;
import org.openqa.selenium.NoSuchElementException;

/**
 * Класс с умными ожиданиями.
 * <p>
 * Класс имеет место быть для остальных сложных ожиданий, например ожидание прогрузки всех элементов одного типа на странице.
 */
public class SmartWait {
    private static final Logger logger = Logger.getInstance();

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition) {
        try {
            selenideElement.shouldNotBe(condition);
        } catch (ElementShouldNot ex) {
            logger.info(String.format("Element %s is not %s", selenideElement.toString(), condition.toString()));
        }
        return !selenideElement.is(condition);
    }


    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementInState(SelenideElement selenideElement, Condition condition) {
        try {
            selenideElement.shouldBe(condition);
        } catch (ElementShould | NoSuchElementException ex) {
            logger.info(String.format("Element %s is %s", selenideElement.toString(), condition.toString()));
        }
        return selenideElement.is(condition);
    }
}

