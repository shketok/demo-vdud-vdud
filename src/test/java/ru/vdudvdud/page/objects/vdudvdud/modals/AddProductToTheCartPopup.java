package ru.vdudvdud.page.objects.vdudvdud.modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.adaptors.selenide.utils.SmartWait;

import static com.codeborne.selenide.Selenide.$;

public class AddProductToTheCartPopup extends PageObject {
    private static final String MAIN_ELEMENT_LOC = "div.cart-popup";

    private SelenideElement confirmBtn = getMainElement().$("button[type='submit']");
    private SelenideElement productQuantity = getMainElement().$("input[name='quantity']");
    private SelenideElement productSelectedSize = getMainElement()
            .$x(".//input[@name='sku_id' and contains(@value, //div[@data-sku-id]/@data-sku-id)]" +
                    "/following-sibling::span[@itemprop='name']");
    private SelenideElement productSelectedModel = getMainElement().$("div.options__content ul");

    /**
     * Конструктор основного элемента.
     */
    public AddProductToTheCartPopup() {
        super($(MAIN_ELEMENT_LOC));
    }

    /**
     * Клик по кнопке подтверждения параметров и количества продукта.
     * Так как форма может не появляться для некоторых групп товаров, то происходит проверка,
     * появилась ли форма на страницы, и кликает только тогда, когда форма появляется.
     */
    public void clickConfirmBtnIfAppeared() {
        if (SmartWait.isElementInState(confirmBtn, Condition.visible)) {
            confirmBtn.click();
        }

    }

    public void setProductQuantity(String value) {
        productQuantity.setValue(value);
    }

    public String getProductSelectedSizeText() {
        return productSelectedSize.getText();
    }

    public boolean isProductSelectedSizeInState(Condition condition) {
        return productSelectedSize.is(condition);
    }

    public String getProductSelectedModelText() {
        return productSelectedModel.getText();
    }

    public boolean isProductSelectedModelInState(Condition condition) {
        return productSelectedModel.is(condition);
    }

    public String getProductQuantityText() {
        return productQuantity.getValue();
    }

    public boolean isProductQuantityInState(Condition condition) {
        return SmartWait.isElementInState(productQuantity, condition);
    }

}
