package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий форму оформления заказов конечного с доставкой, покупателем, типом доставки.
 */
public class CheckoutForm extends BasePage {
    private static final String MAIN_ELEMENT = "div#js-order-form";

    @Override
    protected SelenideElement getMainElement() {
        return $(MAIN_ELEMENT);
    }
}
