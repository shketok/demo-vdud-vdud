package ru.vdudvdud.page.objects.vdudvdud.pages;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout.CheckoutForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.TotalForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.ProductsForm;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс по работе с основной корзиной пользователя.
 */
public class CartPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("section#js-order-cart");

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public ProductsForm getProductsForm() {
        return new ProductsForm();
    }

    public TotalForm getTotalForm() {
        return new TotalForm();
    }

    public CheckoutForm getCheckoutForm() {
        return new CheckoutForm();
    }

}
