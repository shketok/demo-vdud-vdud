package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.objects.vdudvdud.forms.cart.products.product.ProductForm;
import ru.vdudvdud.objects.vdudvdud.pages.CartPage;
import ru.vdudvdud.testdata.models.essences.Product;

public class CartSteps extends BaseSteps {
    private CartPage cartPage = new CartPage();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        cartPage.getCheckoutForm().shouldBe(Condition.visible);
        cartPage.getProductsForm().shouldBe(Condition.visible);
        cartPage.getTotalForm().shouldBe(Condition.visible);

    }

    @Step("Проверка добавления товара в корзину. " +
            "Изображение, название, размер, вес, количество, кнопки и цена отображаются корректно")
    public void checkThatProductWasAddedToTheCart(Product product) {
        ProductForm productForm = cartPage.getProductsForm().getProductForm(product.getName(), product.getModel());

        productForm.checkThatProductImageLocInState(Condition.visible);
        productForm.checkThatProductNameLocInState(Condition.visible);
        productForm.checkThatProductSizeLocInState(Condition.visible);
        productForm.checkThatProductWeightLocInState(Condition.visible);
        productForm.checkThatProductQuantitySectionLocInState(Condition.visible);
        productForm.checkThatProductMinusBtnLocInState(Condition.visible);
        productForm.checkThatProductPlusBtnLocInState(Condition.visible);
        productForm.checkThatProductQuantityInputLocInState(Condition.visible);
        productForm.checkThatProductFullPriceLocInState(Condition.visible);
        productForm.checkThatProductDeleteLocInState(Condition.visible);
    }

    @Step("Проверка общей стоимости товара и его количества в корзине")
    public void checkThatCartProductTabContainsCorrectData(Product product, Integer expectedCount) {
        ProductForm productForm = cartPage.getProductsForm().getProductForm(product.getName(), product.getModel());

        softAssert.assertEquals(productForm.getProductFullPriceText().intValue(), product.getCost() * expectedCount,
                "Ожидаемая стоимость товара не соответствует реальной");
        softAssert.assertEquals(productForm.getProductQuantityInputValue(), expectedCount,
                "Ожидаемое количество товара не соответствует реальному");
        softAssert.assertAll();

    }
}
