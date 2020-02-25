package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.product;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.constants.Delimiters;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.utils.RegexMatcher;

import static com.codeborne.selenide.Selenide.$x;


/**
 * Класс по работе с продуктами в корзине
 */
public class ProductForm extends BasePage {
    /**
     * Локаторы для обращения к элементам конкретного продукта
     */
    private SelenideElement PRODUCT;

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

    /**
     * Создает табу с конкретным продуктом.
     * Обязательна передача имени и размера, так как продукты разных размеров передаются в разные табы
     *
     * @param name Наименование продукта.
     * @param size Размер продукта.
     */
    public ProductForm(String name, String size) {
        PRODUCT = $x(String.format("//div[contains(@class, 'wa-product') and @data-id  " +
                "and .//a[@class='wa-name' and contains(text(), '%s')] " +
                "and .//span[@class='wa-sku' and contains(text(), '%s')]]", name, size));
    }

    /**
     * Создает табу с конкретным продуктом.
     * Обязательна передача имени, продукт может не иметь размера, например кошелек
     *
     * @param name Наименование продукта.
     */
    public ProductForm(String name) {
        PRODUCT = $x(String.format("//div[contains(@class, 'wa-product') and @data-id  " +
                "and .//a[@class='wa-name' and contains(text(), '%s')]]", name));
    }

    @Override
    protected SelenideElement getMainElement() {
        return PRODUCT;
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

    public String getProductWeightText() {
        return PRODUCT.$(PRODUCT_WEIGHT_LOC).getText();
    }

    public Integer getProductQuantityInputValue() {
        return Integer.parseInt(PRODUCT.$(PRODUCT_QUANTITY_INPUT_LOC).getValue());
    }

    public Integer getProductFullPriceText() {
        String fullPriceText = RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(
                PRODUCT.$(PRODUCT_FULL_PRICE_LOC).getText(), RegexPatterns.DIGITS.toString());
        return Integer.parseInt(fullPriceText);
    }
}
