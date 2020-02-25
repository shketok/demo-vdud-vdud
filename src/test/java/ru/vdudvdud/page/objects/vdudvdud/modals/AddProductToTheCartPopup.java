package ru.vdudvdud.page.objects.vdudvdud.modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.adaptors.selenide.utils.SmartWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddProductToTheCartPopup extends BasePage {

    private static final SelenideElement MAIN_ELEMENT = $("div.cart-popup");

    private static final String CONFIRM_BTN_LOC = "button[type='submit']";
    private static final String COUNT_OF_THE_GOOD_LOC = "input[name='quantity']";
    private static final String PRODUCT_SELECTED_SIZE_LOC = "input[name='sku_id'][checked] ~ span[itemprop='name']";
    private static final String PRODUCT_SELECTED_MODEL_LOC = "div.options__content ul";

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void clickConfirmBtn() {
        SelenideElement confirmBtn = MAIN_ELEMENT.$(CONFIRM_BTN_LOC);
        if (SmartWait.isElementInState(confirmBtn, Condition.visible)) {
            MAIN_ELEMENT.$(CONFIRM_BTN_LOC).click();
        }

    }

    public void fillCountOfTheGood(String value) {
        MAIN_ELEMENT.$(COUNT_OF_THE_GOOD_LOC).setValue(value);
    }

    public String getProductSelectedSizeText() {
        return MAIN_ELEMENT.$(PRODUCT_SELECTED_SIZE_LOC).getText();
    }

    public boolean isProductSelectedSizeInState(Condition condition) {
        return MAIN_ELEMENT.$(PRODUCT_SELECTED_SIZE_LOC).is(condition);
    }

    public String getProductSelectedModelText() {
        return MAIN_ELEMENT.$(PRODUCT_SELECTED_MODEL_LOC).getText();
    }

    public boolean isProductSelectedModelInState(Condition condition) {
        return MAIN_ELEMENT.$(PRODUCT_SELECTED_MODEL_LOC).is(condition);
    }

    public String getCountOfTheGoodText() {
        return MAIN_ELEMENT.$(COUNT_OF_THE_GOOD_LOC).getValue();
    }

    public boolean isCountOfTheGoodInState(Condition condition) {
        return MAIN_ELEMENT.$(COUNT_OF_THE_GOOD_LOC).is(condition);
    }
}