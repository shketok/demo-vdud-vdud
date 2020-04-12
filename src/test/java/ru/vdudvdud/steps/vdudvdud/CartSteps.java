package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import ru.vdudvdud.objects.vdudvdud.forms.cart.EmptyCartForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.product.ProductForm;
import ru.vdudvdud.page.objects.vdudvdud.pages.CartPage;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.objects.Cart;

import java.util.Map;

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
        if (!StringUtils.isEmpty(product.getModel())) {
            productForm.checkThatProductSizeLocInState(Condition.visible);
        }
        productForm.checkThatProductWeightLocInState(Condition.visible);
        productForm.checkThatProductQuantitySectionLocInState(Condition.visible);
        productForm.checkThatProductMinusBtnLocInState(Condition.visible);
        productForm.checkThatProductPlusBtnLocInState(Condition.visible);
        productForm.checkThatProductQuantityInputLocInState(Condition.visible);
        productForm.checkThatProductFullPriceLocInState(Condition.visible);
        productForm.checkThatProductDeleteLocInState(Condition.visible);
    }


    @Step("Проверка того, что корзина пустая")
    public void checkThatCartIsEmpty() {
        EmptyCartForm emptyCartForm = cartPage.getEmptyCartForm();

        emptyCartForm.checkThatEmptyCartTextInState(Condition.visible);
        emptyCartForm.checkThatEmptyCartSloganInState(Condition.visible);
        emptyCartForm.checkThatEmptyCartIllustrationInState(Condition.visible);

    }

    @Step("Проверка общей стоимости товара и его количества в корзине")
    public void checkThatCartProductTabContainsCorrectData() {
        for (Map.Entry<String, Product> entry : Cart.getInstance().getProducts().entrySet()) {
            ProductForm productForm = cartPage.getProductsForm().getProductForm(entry.getValue().getName(),
                    entry.getValue().getModel());

            softAssert.assertEquals(productForm.getProductFullPriceText().intValue(), entry.getValue().getCost() * entry.getValue().getCount(),
                    "Ожидаемая стоимость товара не соответствует реальной");
            softAssert.assertEquals(productForm.getProductQuantityInputValue(), Integer.valueOf(entry.getValue().getCount()),
                    "Ожидаемое количество товара не соответствует реальному");
        }
        softAssert.assertAll();
    }


    @Step("Нажатие кнопки удаления у конкретного товара")
    public void deleteProduct(Product product) {
        ProductForm productForm = cartPage.getProductsForm().getProductForm(product.getName(), product.getModel());
        productForm.clickProductDelete();
    }

    @Step("Отмена удаления продукта в модальном окне из корзины")
    public void cancelProductRemoval() {
        cartPage.getProductRemovalPopup().clickCancelProductRemovalBtn();
    }

    @Step("Подтверждение удаления продукта в модальном окне из корзины")
    public void confirmProductRemoval(Product product) {
        cartPage.getProductRemovalPopup().clickConfirmProductRemovalBtn();
        Cart.getInstance().removeProduct(product);
    }

    @Step("Установка значения количества товара в корзине и обновление модели продукта")
    public void setProductCount(Product product, int quantity) {
        ProductForm productForm = cartPage.getProductsForm().getProductForm(product.getName(), product.getModel());
        productForm.fillProductQuantityInput(String.valueOf(quantity));
        product.setCount(quantity);
        Cart.getInstance().putProduct(product);
    }

    @Step("Изменение значения количества товара нажатием кнопки минус в корзине и обновление модели продукта")
    public void clickMinusBtn(Product product) {
        ProductForm productForm = cartPage.getProductsForm().getProductForm(product.getName(), product.getModel());
        productForm.clickProductMinusBtn();
        product.setCount(product.getCount() - 1);
        Cart.getInstance().putProduct(product);
    }


    @Step("Отмена удаления продукта в модальном окне из корзины путем нажатия кнопки закрыть")
    public void closeProductRemoval() {
        cartPage.getProductRemovalPopup().clickCloseProductRemovalPopupBtn();
    }
}
