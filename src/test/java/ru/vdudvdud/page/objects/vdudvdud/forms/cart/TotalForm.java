package ru.vdudvdud.page.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий элементы формы с вводом купоном и итогов по товару
 */
public class TotalForm extends PageObject {
    private static final String MAIN_ELEMENT_LOC = "div.wa-cart-details";

    /**
     * Элементы для взаимодействия с полем ввода данных о купоне.
     */
    private SelenideElement couponCodeInput = getMainElement().$("input.js-coupon-code");
    private SelenideElement useCouponCodeBtn = getMainElement().$("button.js-use-coupon");

    /**
     * Информация о итоговой цене, весе и количестве товара
     */
    private SelenideElement totalPriceLbl = getMainElement().$("*#wa-cart-total");
    private SelenideElement totalProductCountAndWeight = getMainElement().$("*#wa-cart-weight");
    private SelenideElement totalProductsCountLbl = totalProductCountAndWeight.$(".wa-count");
    private SelenideElement totalProductsWeightLbl = totalProductCountAndWeight.$(".wa-weight");

    /**
     * Конструктор основного элемента.
     */
    public TotalForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void checkThatCouponCodeInputInState(Condition condition) {
        couponCodeInput.shouldBe(condition);
    }

    public void checkThatUseCouponCodeBtnInState(Condition condition) {
        useCouponCodeBtn.shouldBe(condition);
    }

    public void checkThatTotalPriceLblInState(Condition condition) {
        totalPriceLbl.shouldBe(condition);
    }

    public void checkThatTotalProductsCountAndWeightLblInState(Condition condition) {
        totalProductCountAndWeight.shouldBe(condition);
    }

    public void checkThatTotalProductsCountLblInState(Condition condition) {
        totalProductsCountLbl.shouldBe(condition);
    }

    public void checkThatTotalProductsWeightLblInState(Condition condition) {
        totalProductsWeightLbl.shouldBe(condition);
    }

    public void clickUseCouponCodeBtn() {
        useCouponCodeBtn.click();
    }

    public String getUseCouponCodeBtnText() {
        return useCouponCodeBtn.getText();
    }

    public String getTotalPriceLblText() {
        return totalPriceLbl.getText();
    }

    public String getTotalProductsCountAndWeightLblText() {
        return totalProductCountAndWeight.getText();
    }

    public String getTotalProductsCountLblText() {
        return totalProductsCountLbl.getText();
    }

    public String getTotalProductsWeightLblText() {
        return totalProductsWeightLbl.getText();
    }

    public void fillCouponCodeInput(String value) {
        couponCodeInput.setValue(value);
    }
}
