package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.util.Arrays;

/**
 * Класс с умными ожиданиями.
 * <p>
 * Класс имеет место быть для остальных сложных ожиданий, например ожидание прогрузки всех элементов одного типа на странице.
 */

// TODO refactor this class according to https://github.com/shketok/demo-vdud-vdud/pull/35 comments
public class SmartWait {
    private static final Logger logger = Logger.getInstance();

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элеменита.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition) {
        try {
            Selenide.Wait().until(driver -> !selenideElement.is(condition));
            return !selenideElement.is(condition);
        } catch (TimeoutException | NoSuchElementException | ElementNotFound e) {
            logger.info(String.format("Element %1$s is not %2$s; selenideElement.is(not(%2$s)) = %3$s",
                    selenideElement.toString(), condition.toString(), !selenideElement.is(condition)));
            return !selenideElement.is(condition);
        }
    }

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элеменита.
     * @param timeMillis      Время ожидания.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition, long timeMillis) {
        // Если ставить ожидание waitUntil, то в таком случае нельзя будет проверить отрицательные действие,
        // вследствии этого возникает ошибка
        long defaultTimeout = com.codeborne.selenide.Configuration.timeout;
        com.codeborne.selenide.Configuration.timeout = timeMillis;
        boolean isNotInState = isElementNotInState(selenideElement, condition);
        com.codeborne.selenide.Configuration.timeout = defaultTimeout;
        return isNotInState;
    }

    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementsInState(Condition condition, SelenideElement... selenideElements) {
        try {
            Selenide.Wait().until(driver -> isAllElementsMatchCondition(condition, selenideElements));
            return isAllElementsMatchCondition(condition, selenideElements);
        } catch (TimeoutException e) {
            Arrays.stream(selenideElements)
                    .forEach(selenideElement ->
                            logger.info(String.format("Element %1$s is %2$s; selenideElement.is(%2$s) = %3$s",
                                    selenideElement.toString(), condition.toString(), selenideElement.is(condition))));
            return isAllElementsMatchCondition(condition, selenideElements);
        }
    }

    private static boolean isAllElementsMatchCondition(Condition condition, SelenideElement... selenideElements) {
        return Arrays.stream(selenideElements)
                .allMatch(selenideElement -> selenideElement.is(condition));
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
            Selenide.Wait().until(driver -> selenideElement.is(condition));
            return selenideElement.is(condition);
        } catch (TimeoutException | NoSuchElementException | ElementNotFound e) {
            logger.info(String.format("Element %1$s is %2$s; selenideElement.is(%2$s) = %3$s",
                    selenideElement.toString(), condition.toString(), selenideElement.is(condition)));
            return selenideElement.is(condition);
        }
    }

    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @param timeMillis      Время ожидания.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementInState(SelenideElement selenideElement, Condition condition, long timeMillis) {
        // Если ставить ожидание waitUntil, то в таком случае нельзя будет проверить отрицательные действие,
        // вследствии этого возникает ошибка
        long defaultTimeout = com.codeborne.selenide.Configuration.timeout;
        com.codeborne.selenide.Configuration.timeout = timeMillis;
        boolean isInState = isElementInState(selenideElement, condition);
        com.codeborne.selenide.Configuration.timeout = defaultTimeout;
        return isInState;
    }
}

