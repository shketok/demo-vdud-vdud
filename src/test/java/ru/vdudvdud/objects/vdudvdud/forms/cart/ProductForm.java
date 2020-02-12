package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


/**
 * Класс по работе с продуктами в корзине
 */
public class ProductForm extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("div.wa-products");

    /**
     * Локаторы для обращения к элементам конкретного продукта
     */
    private static SelenideElement PRODUCT;

    private static final String PRODUCT_IMAGE_LOC = "div.wa-column-image img";
    private static final String PRODUCT_NAME_LOC = "a.wa-name";
    private static final String PRODUCT_SIZE_LOC = "span.wa-sku";
    private static final String PRODUCT_WEIGHT_LOC = "span.wa-weight";
    private static final String PRODUCT_QUANTITY_SECTION_LOC = "div.wa-quantity-section";
    private static final String PRODUCT_MINUS_BTN_LOC = "span.js-decrease";
    private static final String PRODUCT_PLUS_BTN_LOC = "span.js-increase";
    private static final String PRODUCT_QUANTITY_INPUT_LOC = "input.js-product-quantity";
    private static final String PRODUCT_FULL_PRICE_LOC = "div.wa-price-total";
    private static final String PRODUCT_DELETE_LOC = "span.js-delete-product";

    public ProductForm() {
    }

    public ProductForm(String name, String size) {
        PRODUCT = $x(String.format("//div[contains(@class, 'wa-product') and @data-id  " +
                "and .//a[@class='wa-name' and contains(text(), '%s')] " +
                "and .//span[@class='wa-sku' and contains(text(), '%s')]]", name, size));
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void checkThatProductImageLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_IMAGE_LOC).shouldBe(condition);
    }

    public void checkThatProductNameLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_NAME_LOC).shouldBe(condition);
    }

    public void checkThatProductSizeLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_SIZE_LOC).shouldBe(condition);
    }

    public void checkThatProductWeightLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_WEIGHT_LOC).shouldBe(condition);
    }

    public void checkThatProductQuantitySectionLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_QUANTITY_SECTION_LOC).shouldBe(condition);
    }

    public void checkThatProductMinusBtnLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_MINUS_BTN_LOC).shouldBe(condition);
    }

    public void checkThatProductPlusBtnLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_PLUS_BTN_LOC).shouldBe(condition);
    }

    public void checkThatProductQuantityInputLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_QUANTITY_INPUT_LOC).shouldBe(condition);
    }

    public void checkThatProductFullPriceLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_FULL_PRICE_LOC).shouldBe(condition);
    }

    public void checkThatProductDeleteLocInState(Condition condition) {
        PRODUCT.$(PRODUCT_DELETE_LOC).shouldBe(condition);
    }
}
