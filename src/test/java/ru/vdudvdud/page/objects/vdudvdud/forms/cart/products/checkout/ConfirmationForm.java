package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.localization.CartLocalization;

public class ConfirmationForm extends PageObject {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static final String CONFIRMATION_FORM_LOC = "*#wa-step-confirm-section";

    /**
     * Элементы и локаторы по работе с информацией о результатах заказа. Об общей цене доставки и товаров.
     */
    private static final String PRODUCTS_COST_TITLE_PATTERN = ".//div[@class='wa-total-section']//div[contains(@class, 'wa-name') and text()='%s']";
    private static final String PRODUCTS_COST_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.PRODUCTS_COST.getValue());
    private static final String PRODUCTS_DELIVERY_COST_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.DELIVERY_COST.getValue());
    private static final String PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.TOTAL_EXCLUDE_DELIVERY.getValue());

    /**
     * Шаблон локатора до цены продукта
     */
    private static final String PRODUCT_PRICE_LOC = ".//following-sibling::div[@class='wa-value']";

    /**
     * Элементы для установки комментария к заказу.
     */
    private SelenideElement commentToOrderOpenSection = getMainElement().$("*.js-open-section");
    private SelenideElement commentToOrderTextAreaSection = getMainElement().$("*.js-order-comment-field");

    private SelenideElement productsCostTitle = getMainElement().$x(PRODUCTS_COST_TITLE_LOC);
    private SelenideElement productsCostValue = productsCostTitle.$x(PRODUCT_PRICE_LOC);
    private SelenideElement productsDeliveryCostTitle = getMainElement().$x(PRODUCTS_DELIVERY_COST_TITLE_LOC);
    private SelenideElement productsDeliveryCostValue = productsDeliveryCostTitle.$x(PRODUCT_PRICE_LOC);
    private SelenideElement productsTotalExcludeDeliveryTitle = getMainElement().$x(PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE_LOC);
    private SelenideElement productsTotalExcludeDeliveryValue = productsTotalExcludeDeliveryTitle.$x(PRODUCT_PRICE_LOC);

    /**
     * Кнопка подтверждения заказа пользователя.
     */
    private SelenideElement submitOrderBtn = getMainElement().$(".wa-actions-section button.js-submit-order-button");

    /**
     * Конструктор основного элемента.
     */
    public ConfirmationForm(SelenideElement parentMainElement) {
        super(parentMainElement.$(CONFIRMATION_FORM_LOC));
    }

    public void checkThatCommentToOrderOpenSectionInState(Condition condition) {
        commentToOrderOpenSection.shouldBe(condition);
    }

    public void checkThatCommentToOrderTextAreaSectionInState(Condition condition) {
        commentToOrderTextAreaSection.shouldBe(condition);
    }

    public void checkThatProductsCostTitleInState(Condition condition) {
        productsCostTitle.shouldBe(condition);
    }

    public void checkThatProductsCostValueInState(Condition condition) {
        productsCostValue.shouldBe(condition);
    }

    public void checkThatProductsDeliveryCostTitleInState(Condition condition) {
        productsDeliveryCostTitle.shouldBe(condition);
    }

    public void checkThatProductsDeliveryCostValueInState(Condition condition) {
        productsDeliveryCostValue.shouldBe(condition);
    }

    public void checkThatProductsTotalExcludeDeliveryTitleInState(Condition condition) {
        productsTotalExcludeDeliveryTitle.shouldBe(condition);
    }

    public void checkThatProductsTotalExcludeDeliveryValueInState(Condition condition) {
        productsTotalExcludeDeliveryValue.shouldBe(condition);
    }

    public void checkThatSubmitOrderBtnInState(Condition condition) {
        submitOrderBtn.shouldBe(condition);
    }

    public void clickCommentToOrderOpenSection() {
        commentToOrderOpenSection.click();
    }

    public void clickSubmitOrderBtn() {
        submitOrderBtn.click();
    }

    public String getCommentToOrderOpenSectionText() {
        return commentToOrderOpenSection.getText();
    }

    public String getCommentToOrderTextAreaSectionText() {
        return commentToOrderTextAreaSection.getText();
    }

    public String getProductsCostTitleText() {
        return productsCostTitle.getText();
    }

    public String getProductsCostValueText() {
        return productsCostValue.getText();
    }

    public String getProductsDeliveryCostTitleText() {
        return productsDeliveryCostTitle.getText();
    }

    public String getProductsDeliveryCostValueText() {
        return productsDeliveryCostValue.getText();
    }

    public String getProductsTotalExcludeDeliveryTitleText() {
        return productsTotalExcludeDeliveryTitle.getText();
    }

    public String getProductsTotalExcludeDeliveryValueText() {
        return productsTotalExcludeDeliveryValue.getText();
    }

    public String getSubmitOrderBtnText() {
        return submitOrderBtn.getText();
    }

    public void fillCommentToOrderTextAreaSection(String value) {
        commentToOrderTextAreaSection.setValue(value);
    }
}
