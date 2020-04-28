package ru.vdudvdud.adaptors.selenide.conditions;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.collections.SizeGreaterThanOrEqual;
import ru.vdudvdud.adaptors.selenide.conditions.collection.StopAppearNew;

/**
 * Класс-обертка для получения ожиданий не входящих в библиотеку Selenide.
 * Класс служит для получения специфических ожиданий нескольких элементов ElementsCollection.
 */
public class CustomCollectionCondition {
    public static final CollectionCondition sizeGreaterThanOrEqualOne = new SizeGreaterThanOrEqual(1);

    public static StopAppearNew stopAppearNew() {
        return new StopAppearNew();
    }
}
