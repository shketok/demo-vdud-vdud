package ru.vdudvdud.testdata.objects.vdudvdud.pages;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс описывающий основную страницу при переходе по базовому урлу до сайта. Страница отображающая товары и табы для переключения по категориям.
 */
public class VdudMainPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $x("//div[contains(@class, 'nc-items') and .//div[@class='nc-item']]");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }
}
