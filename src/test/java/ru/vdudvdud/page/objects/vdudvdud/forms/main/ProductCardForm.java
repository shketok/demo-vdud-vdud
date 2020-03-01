package ru.vdudvdud.page.objects.vdudvdud.forms.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.constants.CssAttributes;

public class ProductCardForm extends PageObject {

    private static final String PRODUCT_BY_NAME_PATTERN = ".//div[@class='nc-item' and .//span[@itemprop='name' and contains(text(), '%s')]]";

    private SelenideElement productStatus  = getMainElement().$x(".//span//div[contains(@class, 'badge')]//span");
    private SelenideElement productImage  = getMainElement().$x(".//div[@class='item__actions']//following-sibling::a[contains(@class, 'item__image')]//img[@class='item__img']");
    private SelenideElement productName  = getMainElement().$x(".//span[@itemprop='name']");
    private SelenideElement productPriceAndCurrency  = getMainElement().$x(".//div[@class='item__main']//div[@class='prc']");
    private SelenideElement productExistence  = getMainElement().$x(".//div[@class='item__main']//span[@class='stock-text']");
    private SelenideElement productToTheProductPage  = getMainElement().$x(".//div[@class='item__main']//button[@type='submit']");
    private SelenideElement productToTheCartBottomBtn  = getMainElement().$x(".//div[@class='item__bottom']//button[@type='submit']");


    public ProductCardForm(SelenideElement parentMainElement) {
        super(parentMainElement);
    }

    public ProductCardForm(SelenideElement parentMainElement, String productName) {
        super(parentMainElement.$x(String.format(PRODUCT_BY_NAME_PATTERN, productName)));
    }

    public void checkThatProductCardInState(Condition condition) {
        mainElement.shouldBe(condition);
    }

    public void checkThatProductStatusInState(Condition condition) {
        productStatus.shouldBe(condition);
    }

    public void checkThatProductImageInState(Condition condition) {
        productImage.shouldBe(condition);
    }

    public void checkThatProductNameInState(Condition condition) {
        productName.shouldBe(condition);
    }

    public void checkThatProductPriceAndCurrencyInState(Condition condition) {
        productPriceAndCurrency.shouldBe(condition);
    }

    public void checkThatProductExistenceInState(Condition condition) {
        productExistence.shouldBe(condition);
    }

    public void checkThatProductToTheProductPageInState(Condition condition) {
        productToTheProductPage.shouldBe(condition);
    }

    public void checkThatProductToTheCartBottomBtnInState(Condition condition) {
        productToTheCartBottomBtn.shouldBe(condition);
    }

    public String getProductStatusText() {
        return productStatus.getText();
    }

    public String getProductImageSource() {
        return productImage.getAttribute(CssAttributes.SRC);
    }

    public String getProductNameText() {
        return productName.getText();
    }

    public String getProductPriceAndCurrencyText() {
        return productPriceAndCurrency.getText();
    }

    public String getProductExistenceText() {
        return productExistence.getText();
    }

    public String getProductToTheProductPageText() {
        return productToTheProductPage.getText();
    }

    public String getProductToTheCartBottomBtnText() {
        return productToTheCartBottomBtn.getText();
    }

    public void clickProductToTheProductPage() {
        productToTheProductPage.click();
    }

    public void clickProductToTheCartBottomBtn() {
        productToTheCartBottomBtn.click();
    }

    public boolean isProductStatusInState(Condition condition) {
        return productStatus.is(condition);
    }
}
