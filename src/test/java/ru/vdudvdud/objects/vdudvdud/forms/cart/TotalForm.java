package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий элементы формы с вводом купоном и итогов по товару
 */
public class TotalForm extends BasePage {
    private static final String MAIN_ELEMENT = "div.wa-cart-details";

    @Override
    protected SelenideElement getMainElement() {
        return $(MAIN_ELEMENT);
    }
}
