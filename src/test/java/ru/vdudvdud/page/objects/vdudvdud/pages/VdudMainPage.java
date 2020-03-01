package ru.vdudvdud.page.objects.vdudvdud.pages;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.page.objects.vdudvdud.forms.main.ProductCardsForm;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс описывающий основную страницу при переходе по базовому урлу до сайта. Страница отображающая товары и табы для переключения по категориям.
 */
public class VdudMainPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = "//*[@class='home-main']";

    /**
     * Конструктор основного элемента.
     */
    public VdudMainPage() {
        super($x(MAIN_ELEMENT_LOC));
    }

    public ProductCardsForm getProductCardsForm() {
        return new ProductCardsForm(getMainElement());
    }


}
