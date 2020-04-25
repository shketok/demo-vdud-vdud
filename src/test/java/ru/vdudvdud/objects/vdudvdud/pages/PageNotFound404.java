package ru.vdudvdud.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$x;

import ru.vdudvdud.adaptors.selenide.base.BasePage;

/**
 * Класс, описывающий 404 страницу
 */
public class PageNotFound404 extends BasePage {

    private static final String MAIN_ELEMENT = "//div[@class='not-found']";

    /**
     * Конструктор основного элемента.
     */
    public PageNotFound404() {
        super($x(MAIN_ELEMENT));
    }
}
