package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.product;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.utils.RegexMatcher;

import static com.codeborne.selenide.Selenide.$x;


/**
 * Класс по работе с продуктами в корзине
 */
public class ProductForm extends PageObject {
    /**
     * Локаторы для обращения к элементам конкретного продукта
     */
    private static final String PRODUCT_ELEMENT_BY_NAME_LOC_PATTERN =
            "//div[contains(@class, 'wa-product') and @data-id  "
                    + "and .//a[@class='wa-name' and contains(text(), '%s')]]";
    private static final String PRODUCT_ELEMENT_BY_NAME_AND_SIZE_LOC_PATTERN =
            "//div[contains(@class, 'wa-product') and @data-id  "
                    + "and .//a[@class='wa-name' and contains(text(), '%s')] "
                    + "and .//span[@class='wa-sku' and contains(text(), '%s')]]";

    private SelenideElement productImage = getMainElement().$("div.wa-column-image img");
    private SelenideElement productName = getMainElement().$("a.wa-name");
    private SelenideElement productSize = getMainElement().$("span.wa-sku");
    private SelenideElement productWeight = getMainElement().$("span.wa-weight");
    private SelenideElement productQuantitySection = getMainElement().$("div.wa-quantity-section");
    private SelenideElement productMinusBtn = getMainElement().$("span.js-decrease");
    private SelenideElement productPlusBtn = getMainElement().$("span.js-increase");
    private SelenideElement productQuantityInput = getMainElement().$("input.js-product-quantity");
    private SelenideElement productFullPrice = getMainElement().$("div.wa-price-total");
    private SelenideElement productDelete = getMainElement().$("span.js-delete-product");

    /**
     * Создает табу с конкретным продуктом.
     * Обязательна передача имени, продукт может не иметь размера, например кошелек
     *
     * @param name Наименование продукта.
     */
    public ProductForm(String name) {
        super($x(String.format(PRODUCT_ELEMENT_BY_NAME_LOC_PATTERN, name)));
    }

    /**
     * Создает табу с конкретным продуктом.
     * Обязательна передача имени и размера, так как продукты разных размеров передаются в разные табы
     *
     * @param name Наименование продукта.
     * @param size Размер продукта.
     */
    public ProductForm(String name, String size) {
        super($x(String.format(PRODUCT_ELEMENT_BY_NAME_AND_SIZE_LOC_PATTERN, name, size)));
    }

    public void checkThatProductImageLocInState(Condition condition) {
        productImage.shouldBe(condition);
    }

    public void checkThatProductNameLocInState(Condition condition) {
        productName.shouldBe(condition);
    }

    public void checkThatProductSizeLocInState(Condition condition) {
        productSize.shouldBe(condition);
    }

    public void checkThatProductWeightLocInState(Condition condition) {
        productWeight.shouldBe(condition);
    }

    public void checkThatProductQuantitySectionLocInState(Condition condition) {
        productQuantitySection.shouldBe(condition);
    }

    public void checkThatProductMinusBtnLocInState(Condition condition) {
        productMinusBtn.shouldBe(condition);
    }

    public void checkThatProductPlusBtnLocInState(Condition condition) {
        productPlusBtn.shouldBe(condition);
    }

    public void checkThatProductQuantityInputLocInState(Condition condition) {
        productQuantityInput.shouldBe(condition);
    }

    public void checkThatProductFullPriceLocInState(Condition condition) {
        productFullPrice.shouldBe(condition);
    }

    public void checkThatProductDeleteLocInState(Condition condition) {
        productDelete.shouldBe(condition);
    }

    public String getProductWeightText() {
        return productWeight.getText();
    }

    public Integer getProductQuantityInputValue() {
        return Integer.parseInt(productQuantityInput.getValue());
    }

    public Integer getProductFullPriceText() {
        String fullPriceText = RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(
                productFullPrice.getText(), RegexPatterns.DIGITS.toString());
        return Integer.parseInt(fullPriceText);
    }

    public void clickProductDelete() {
        productDelete.click();
    }
}
