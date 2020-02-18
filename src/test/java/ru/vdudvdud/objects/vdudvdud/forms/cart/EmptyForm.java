package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий элементы формы пустой корзины
 */
public class EmptyForm extends BasePage {

    private static final String MAIN_ELEMENT = "div.cart-empty";
    private static final String EMPTY_CART_LOC = "div.cart-empty__text";

    @Override
    protected SelenideElement getMainElement() {
        return $(MAIN_ELEMENT);
    }

    public void checkCartIsEmpty() {
        $(EMPTY_CART_LOC).shouldBe(Condition.visible);
    }
}
