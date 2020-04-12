package ru.vdudvdud.adaptors.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

/**
 * Класс изменяющий поведение базовых команд селенайда с помощью изменения конфигурации на момент вызов функции
 */
public class SelenideElementCommandsDecorator {


    /**
     * Установка значения в поле ввода с помощью джава скрипта и вызова событий.
     * @param selenideElement Элемент для взаимодействия.
     * @param value Значение устанавливаемое в элемент.
     */
    public static SelenideElement fastSetValue(SelenideElement selenideElement, String value) {
        Configuration.fastSetValue = true;
        selenideElement.setValue(value);
        Configuration.fastSetValue = false;
        return selenideElement;
    }

    /**
     * Клик по элементу с использованием джаваскрипта
     * @param selenideElement элемента для клика.
     * @return элемент по которому был клик.
     */
    public static SelenideElement clickViaJs(SelenideElement selenideElement) {
        com.codeborne.selenide.Configuration.clickViaJs = true;
        selenideElement.click();
        com.codeborne.selenide.Configuration.clickViaJs = false;
        return selenideElement;
    }
}
