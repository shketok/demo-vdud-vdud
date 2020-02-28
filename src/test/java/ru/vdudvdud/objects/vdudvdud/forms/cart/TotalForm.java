package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий элементы формы с вводом купоном и итогов по товару
 */
public class TotalForm extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("div.wa-cart-details");

    /**
     * Элементы для взаимодействия с полем ввода данных о купоне.
     */
    private final SelenideElement COUPON_CODE_INPUT = MAIN_ELEMENT.$("input.js-coupon-code");
    private final SelenideElement USE_COUPON_CODE_BTN = MAIN_ELEMENT.$("button.js-use-coupon");

    /**
     * Информация о итоговой цене, весе и количестве товара
     */
    private final SelenideElement TOTAL_PRICE_LBL = MAIN_ELEMENT.$("*#wa-cart-total");
    private final SelenideElement TOTAL_PRODUCTS_COUNT_AND_WEIGHT_LBL = MAIN_ELEMENT.$("*#wa-cart-weight");
    private final SelenideElement TOTAL_PRODUCTS_COUNT_LBL = TOTAL_PRODUCTS_COUNT_AND_WEIGHT_LBL.$(".wa-count");
    private final SelenideElement TOTAL_PRODUCTS_WEIGHT_LBL = TOTAL_PRODUCTS_COUNT_AND_WEIGHT_LBL.$(".wa-weight");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void checkThatCouponCodeInputInState(Condition condition) {
        COUPON_CODE_INPUT.shouldBe(condition);
    }

    public void checkThatUseCouponCodeBtnInState(Condition condition) {
        USE_COUPON_CODE_BTN.shouldBe(condition);
    }

    public void checkThatTotalPriceLblInState(Condition condition) {
        TOTAL_PRICE_LBL.shouldBe(condition);
    }

    public void checkThatTotalProductsCountAndWeightLblInState(Condition condition) {
        TOTAL_PRODUCTS_COUNT_AND_WEIGHT_LBL.shouldBe(condition);
    }

    public void checkThatTotalProductsCountLblInState(Condition condition) {
        TOTAL_PRODUCTS_COUNT_LBL.shouldBe(condition);
    }

    public void checkThatTotalProductsWeightLblInState(Condition condition) {
        TOTAL_PRODUCTS_WEIGHT_LBL.shouldBe(condition);
    }

    public void clickUseCouponCodeBtn() {
        USE_COUPON_CODE_BTN.click();
    }

    public String getUseCouponCodeBtnText() {
        return USE_COUPON_CODE_BTN.getText();
    }

    public String getTotalPriceLblText() {
        return TOTAL_PRICE_LBL.getText();
    }

    public String getTotalProductsCountAndWeightLblText() {
        return TOTAL_PRODUCTS_COUNT_AND_WEIGHT_LBL.getText();
    }

    public String getTotalProductsCountLblText() {
        return TOTAL_PRODUCTS_COUNT_LBL.getText();
    }

    public String getTotalProductsWeightLblText() {
        return TOTAL_PRODUCTS_WEIGHT_LBL.getText();
    }

    public void fillCouponCodeInput(String value) {
        COUPON_CODE_INPUT.setValue(value);
    }
}
