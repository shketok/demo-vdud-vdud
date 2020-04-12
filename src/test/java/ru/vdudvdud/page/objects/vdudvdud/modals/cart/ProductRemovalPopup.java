package ru.vdudvdud.page.objects.vdudvdud.modals.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;

import static com.codeborne.selenide.Selenide.$;

public class ProductRemovalPopup extends PageObject {
    private static final String MAIN_ELEMENT_LOC = "div.wa-dialog-body";

    private SelenideElement closeProductRemovalPopupBtn = getMainElement().$(".js-close-dialog");

    private SelenideElement confirmProductRemovalBtn = getMainElement().$(".js-confirm");
    private SelenideElement cancelProductRemovalBtn = getMainElement().$(".js-cancel");



    /**
     * Конструктор основного элемента.
     */
    public ProductRemovalPopup() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void clickCloseProductRemovalPopupBtn() {
        closeProductRemovalPopupBtn.click();
    }

    public void clickConfirmProductRemovalBtn() {
        confirmProductRemovalBtn.click();
    }

    public void clickCancelProductRemovalBtn() {
        cancelProductRemovalBtn.click();
    }

    public void checkThatCloseProductRemovalPopupBtnInState(Condition condition) {
        closeProductRemovalPopupBtn.shouldBe(condition);
    }

    public void checkThatConfirmProductRemovalBtnInState(Condition condition) {
        confirmProductRemovalBtn.shouldBe(condition);
    }

    public void checkThatCancelProductRemovalBtnInState(Condition condition) {
        cancelProductRemovalBtn.shouldBe(condition);
    }
}
