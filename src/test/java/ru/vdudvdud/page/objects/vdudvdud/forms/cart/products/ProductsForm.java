package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products;

import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.product.ProductForm;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsForm extends BasePage {
    private static final String MAIN_ELEMENT = "div.wa-products";

    @Override
    protected SelenideElement getMainElement() {
        return $(MAIN_ELEMENT);
    }

    /**
     * Получение табы с конкретным продуктом из корзины.
     * Обязательна передача имени и размера, так как продукты разных размеров передаются в разные табы
     *
     * @param name Наименование продукта.
     * @param size Размер продукта.
     */
    public ProductForm getProductForm(String name, String size) {
        if (size == null) {
            return new ProductForm(name);
        } else {
            return new ProductForm(name, size);
        }
    }

    public int getProductsCount() {
        return $$(MAIN_ELEMENT).size();
    }
}
