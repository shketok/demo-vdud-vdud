package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.localization.CartLocalization;

public class ConfirmationForm extends BasePage {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static SelenideElement MAIN_ELEMENT;
    private static final String CONFIRMATION_FORM_LOC = "*#wa-step-confirm-section";

    /**
     * Элементы для установки комментария к заказу.
     */
    private final SelenideElement COMMENT_TO_ORDER_OPEN_SECTION = MAIN_ELEMENT.$("*.js-open-section");
    private final SelenideElement COMMENT_TO_ORDER_TEXT_AREA_SECTION = MAIN_ELEMENT.$("*.js-order-comment-field");

    /**
     * Элементы и локаторы по работе с информацией о результатах заказа. Об общей цене доставки и товаров.
     */
    private static final String PRODUCTS_COST_TITLE_PATTERN = "//div[@class='wa-total-section']//div[contains(@class, 'wa-name') and text()='%s']";
    private static final String PRODUCTS_COST_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.PRODUCTS_COST.getValue());
    private static final String PRODUCTS_DELIVERY_COST_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.DELIVERY_COST.getValue());
    private static final String PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE_LOC = String.format(PRODUCTS_COST_TITLE_PATTERN,
            CartLocalization.TOTAL_EXCLUDE_DELIVERY.getValue());

    private static final String PRODUCT_PRICE_LOC = ".//following-sibling::div[@class='wa-value']";

    private final SelenideElement PRODUCTS_COST_TITLE = MAIN_ELEMENT.$x(PRODUCTS_COST_TITLE_LOC);
    private final SelenideElement PRODUCTS_COST_VALUE = PRODUCTS_COST_TITLE.$x(PRODUCT_PRICE_LOC);
    private final SelenideElement PRODUCTS_DELIVERY_COST_TITLE = MAIN_ELEMENT.$x(PRODUCTS_DELIVERY_COST_TITLE_LOC);
    private final SelenideElement PRODUCTS_DELIVERY_COST_VALUE = PRODUCTS_DELIVERY_COST_TITLE.$x(PRODUCT_PRICE_LOC);
    private final SelenideElement PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE = MAIN_ELEMENT.$x(PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE_LOC);
    private final SelenideElement PRODUCTS_TOTAL_EXCLUDE_DELIVERY_VALUE = PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE.$x(PRODUCT_PRICE_LOC);

    /**
     * Кнопка подтверждения заказа пользователя.
     */
    private final SelenideElement SUBMIT_ORDER_BTN = MAIN_ELEMENT.$(".wa-actions-section button.js-submit-order-button");



    public ConfirmationForm(SelenideElement parentMainElement) {
        MAIN_ELEMENT = parentMainElement.$(CONFIRMATION_FORM_LOC);
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void checkThatCommentToOrderOpenSectionInState(Condition condition) {
        COMMENT_TO_ORDER_OPEN_SECTION.shouldBe(condition);
    }

    public void checkThatCommentToOrderTextAreaSectionInState(Condition condition) {
        COMMENT_TO_ORDER_TEXT_AREA_SECTION.shouldBe(condition);
    }

    public void checkThatProductsCostTitleInState(Condition condition) {
        PRODUCTS_COST_TITLE.shouldBe(condition);
    }

    public void checkThatProductsCostValueInState(Condition condition) {
        PRODUCTS_COST_VALUE.shouldBe(condition);
    }

    public void checkThatProductsDeliveryCostTitleInState(Condition condition) {
        PRODUCTS_DELIVERY_COST_TITLE.shouldBe(condition);
    }

    public void checkThatProductsDeliveryCostValueInState(Condition condition) {
        PRODUCTS_DELIVERY_COST_VALUE.shouldBe(condition);
    }

    public void checkThatProductsTotalExcludeDeliveryTitleInState(Condition condition) {
        PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE.shouldBe(condition);
    }

    public void checkThatProductsTotalExcludeDeliveryValueInState(Condition condition) {
        PRODUCTS_TOTAL_EXCLUDE_DELIVERY_VALUE.shouldBe(condition);
    }

    public void checkThatSubmitOrderBtnInState(Condition condition) {
        SUBMIT_ORDER_BTN.shouldBe(condition);
    }

    public void clickCommentToOrderOpenSection() {
        COMMENT_TO_ORDER_OPEN_SECTION.click();
    }

    public void clickSubmitOrderBtn() {
        SUBMIT_ORDER_BTN.click();
    }

    public String getCommentToOrderOpenSectionText() {
        return COMMENT_TO_ORDER_OPEN_SECTION.getText();
    }

    public String getCommentToOrderTextAreaSectionText() {
        return COMMENT_TO_ORDER_TEXT_AREA_SECTION.getText();
    }

    public String getProductsCostTitleText() {
        return PRODUCTS_COST_TITLE.getText();
    }

    public String getProductsCostValueText() {
        return PRODUCTS_COST_VALUE.getText();
    }

    public String getProductsDeliveryCostTitleText() {
        return PRODUCTS_DELIVERY_COST_TITLE.getText();
    }

    public String getProductsDeliveryCostValueText() {
        return PRODUCTS_DELIVERY_COST_VALUE.getText();
    }

    public String getProductsTotalExcludeDeliveryTitleText() {
        return PRODUCTS_TOTAL_EXCLUDE_DELIVERY_TITLE.getText();
    }

    public String getProductsTotalExcludeDeliveryValueText() {
        return PRODUCTS_TOTAL_EXCLUDE_DELIVERY_VALUE.getText();
    }

    public String getSubmitOrderBtnText() {
        return SUBMIT_ORDER_BTN.getText();
    }

    public void fillCommentToOrderTextAreaSection(String value) {
        COMMENT_TO_ORDER_TEXT_AREA_SECTION.setValue(value);
    }
}
