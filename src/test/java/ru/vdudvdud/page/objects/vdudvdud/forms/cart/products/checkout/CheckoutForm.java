package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import ru.vdudvdud.adaptors.selenide.base.PageObject;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий форму оформления заказов конечного с доставкой, покупателем, типом доставки.
 */
public class CheckoutForm extends PageObject {
    private static final String MAIN_ELEMENT_LOC = "div#js-order-form";

    /**
     * Конструктор основного элемента.
     */
    public CheckoutForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    public BuyerForm getBuyerForm() {
        return new BuyerForm(getMainElement());
    }

    public DeliveryForm getDeliveryForm() {
        return new DeliveryForm(getMainElement());
    }

    public ConfirmationForm getConfirmationForm() {
        return new ConfirmationForm(getMainElement());
    }
}
