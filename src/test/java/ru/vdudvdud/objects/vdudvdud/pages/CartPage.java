package ru.vdudvdud.objects.vdudvdud.pages;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.objects.vdudvdud.forms.cart.CheckoutForm;
import ru.vdudvdud.objects.vdudvdud.forms.cart.ProductForm;
import ru.vdudvdud.objects.vdudvdud.forms.cart.TotalForm;

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

    public ProductForm getProductForm() {
        return new ProductForm();
    }
    public ProductForm getProductForm(String name, String size) {
        return new ProductForm(name, size);
    }

    public TotalForm getTotalForm() {
        return new TotalForm();
    }

    public CheckoutForm getCheckoutForm() {
        return new CheckoutForm();
    }

}
