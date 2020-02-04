package ru.vdudvdud.adaptors.selenide.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;

/**
 * Класс с умными ожиданиями, которые ждут элемента столько же, сколько shouldBe функции, но при этом обрабатывает ошибки.
 * Класс имеет смысл использовать только в случае негативных проверок, когда мы знаем, что чего-то не должно появиться,
 * так как классическое ожидание в случае, если элемент не видим в данный момент, сразу вернет истину, хотя он может появиться
 * спустя некоторое время.
 */
public class SmartWait {

    /**
     * Функция ожидания, что элемент отсутствует в течении длительного времени.
     * @param selenideElement Элемент для ожидания.
     * @param condition Тип ожидания.
     * @return True - элемент отсутствует в течении длительного времени. False - элемент появился на протяжении времени, хотя ожидали отсутвтие.
     */
    public static boolean isNotElement(SelenideElement selenideElement, Condition condition) {
        try {
            selenideElement.shouldBe(condition);
        } catch (ElementShould | ElementNotFound ignored) {}
        return !selenideElement.is(condition);
    }

    /**
     * Функция ожидания, что элемент присутствует в течении длительного времени.
     * @param selenideElement Элемент для ожидания.
     * @param condition Тип ожидания.
     * @return True - элемент присутствует в течении длительного времени. False - элемент не появился на протяжении времени, хотя ожидали присутствие.
     */
    public static boolean isElement(SelenideElement selenideElement, Condition condition) {
        try {
            selenideElement.shouldNotBe(condition);
        } catch (ElementShouldNot ignored) {}
        return selenideElement.is(condition);
    }
}

