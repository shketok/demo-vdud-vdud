package ru.vdudvdud.objects.vdudvdud.forms.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.reporters.jq.Main;
import ru.vdudvdud.adaptors.selenide.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывающий элементы формы пустой корзины
 */
public class EmptyCartForm extends BasePage {

    private static final String MAIN_ELEMENT = "div.cart-empty";
    private static final String EMPTY_CART_TEXT_LOC = "div.cart-empty__text";
    private static final String EMPTY_CART_SLOGAN_LOC = "div.cart-empty__slogan";
    private static final String EMPTY_CART_ILLUSTRATION_LOC = "div.cart-empty__illustration img";

    public EmptyCartForm() {
        super($(MAIN_ELEMENT));
    }

    public void checkThatEmptyCartTextInState(Condition condition){
        getMainElement().$(EMPTY_CART_TEXT_LOC).shouldBe(condition);
    }

    public void checkThatEmptyCartSloganInState(Condition condition){
        getMainElement().$(EMPTY_CART_SLOGAN_LOC).shouldBe(condition);
    }

    public void checkThatEmptyCartIllustrationInState(Condition condition){
        getMainElement().$(EMPTY_CART_ILLUSTRATION_LOC).shouldBe(condition);
    }
}
