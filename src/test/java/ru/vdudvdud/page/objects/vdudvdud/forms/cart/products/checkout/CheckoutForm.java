package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий форму оформления заказов конечного с доставкой, покупателем, типом доставки.
 */
public class CheckoutForm extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("div#js-order-form");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public BuyerForm getBuyerForm() {
        return new BuyerForm(MAIN_ELEMENT);
    }

    public DeliveryForm getDeliveryForm() {
        return new DeliveryForm(MAIN_ELEMENT);
    }

    public ConfirmationForm getConfirmationForm() {
        return new ConfirmationForm(MAIN_ELEMENT);
    }
}
