package ru.vdudvdud.page.objects.vdudvdud.modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;

import static com.codeborne.selenide.Selenide.$;

public class ProductAddedToTheCartPopup extends PageObject {

    private static final String MAIN_ELEMENT_LOC = "div.cart-add-popup";

    private SelenideElement closeBtn = getMainElement().$("button[class='mfp-close']");
    private SelenideElement toCartBtn = getMainElement().$("a[class*='to-cart']");
    private SelenideElement continueBtn = getMainElement().$("a[class*='continue']");

    /**
     * Конструктор основного элемента.
     */
    public ProductAddedToTheCartPopup() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void clickClose() {
        closeBtn.shouldBe(Condition.visible).click();
    }

    public void clickToCart() {
        toCartBtn.click();
    }

    public void clickContinue() {
        continueBtn.click();
    }
}
