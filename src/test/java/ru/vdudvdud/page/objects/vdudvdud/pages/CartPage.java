package ru.vdudvdud.page.objects.vdudvdud.pages;

import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.TotalForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.ProductsForm;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout.CheckoutForm;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс по работе с основной корзиной пользователя.
 */
public class CartPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = "section#js-order-cart";

    /**
     * Конструктор основного элемента.
     */
    public CartPage() {
        super($(MAIN_ELEMENT_LOC));
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
