package ru.vdudvdud.adaptors.selenide.conditions;

import ru.vdudvdud.adaptors.selenide.conditions.collection.StopAppearNew;

/**
 * Класс-обертка для получения ожиданий не входящих в библиотеку Selenide.
 * Класс служит для получения специфических ожиданий нескольких элементов ElementsCollection.
 */
public class CustomCollectionCondition {
    public static StopAppearNew stopAppearNew() {
        return new StopAppearNew();
    }
}
