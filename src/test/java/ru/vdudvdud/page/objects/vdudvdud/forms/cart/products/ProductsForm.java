package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products;

import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.product.ProductForm;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsForm extends PageObject {
    private static final String MAIN_ELEMENT_LOC = "div.wa-products";

    /**
     * Конструктор основного элемента.
     */
    public ProductsForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    /**
     * Получение табы с конкретным продуктом из корзины.
     * Обязательна передача имени и размера, так как продукты разных размеров передаются в разные табы
     *
     * @param name Наименование продукта.
     * @param size Размер продукта.
     */
    public ProductForm getProductForm(String name, String size) {
        return size == null ? new ProductForm(name) : new ProductForm(name, size);
    }

    public int getProductsCount() {
        return $$(MAIN_ELEMENT_LOC).size();
    }
}
