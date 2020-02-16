package ru.vdudvdud.objects.vdudvdud.modals;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ProductAddedToTheCartPopup extends BasePage {

    private static final SelenideElement MAIN_ELEMENT = $("div.cart-add-popup");

    private static final String CLOSE_LOC = "button[class='mfp-close']";
    private static final String TO_CART_LOC = "a[class*='to-cart']";
    private static final String CONTINUE_LOC = "a[class*='continue']";

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void clickClose() {
        MAIN_ELEMENT.$(CLOSE_LOC).click();
    }

    public void clickToCart() {
        MAIN_ELEMENT.$(TO_CART_LOC).click();
    }

    public void clickContinue() {
        MAIN_ELEMENT.$(CONTINUE_LOC).click();
    }
}
