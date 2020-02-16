package ru.vdudvdud.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

public class DeliveryForm extends BasePage {
    private static SelenideElement MAIN_ELEMENT;

    private static final String DELIVERY_FORM_LOC = "#wa-step-region-section";

    public DeliveryForm(SelenideElement parentMainElement) {
        MAIN_ELEMENT = parentMainElement.$(DELIVERY_FORM_LOC);
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }
}