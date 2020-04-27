package ru.vdudvdud.page.objects.vdudvdud.forms.cart;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.testng.asserts.SoftAssert;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.models.essences.MiniCartProduct;
import ru.vdudvdud.testdata.objects.Cart;
import ru.vdudvdud.testdata.utils.RegexMatcher;

public class MiniCartForm extends PageObject {

    private static final String MAIN_ELEMENT_LOC = "div[class='mini-cart']";
    private final String ITEM_NAME = ".//a[@class='mini-cart__title']";
    private final String ITEM_IMG_LINK = ".//a[@class='mini-cart__pic']//img";
    private final String ITEM_CURRENCY = ".//div[@class='mini-cart__price'] //span[@class='ruble']";
    private final String ITEM_COUNT = ".//span[@class='mini-cart__quantity']";
    private final String ITEM_PRICE = ".//div[@class='mini-cart__price']";
    private final Cart cart = Cart.getInstance();

    public ElementsCollection miniCartItems = getMainElement().$$x(".//li[@class='mini-cart__item']");

    public MiniCartForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    public List<MiniCartProduct> getProducts() {
        return miniCartItems.stream().map(item -> {
            MiniCartProduct miniCartProduct = new MiniCartProduct();
            miniCartProduct.setName(item.$x(ITEM_NAME).getText());
            miniCartProduct.setCost(getItemCost(item));
            miniCartProduct.setImgLink(item.$x(ITEM_IMG_LINK).getAttribute("src"));
            miniCartProduct.setCurrency(item.$x(ITEM_CURRENCY).getText());
            miniCartProduct.setCount(Integer.parseInt(item.$x(ITEM_COUNT).getText()));
            return miniCartProduct;
        }).collect(Collectors.toList());
    }


    public void checkThatMiniCartDataIsCorrect() {
        SoftAssert softAssert = new SoftAssert();
        getProducts().forEach(product -> softAssert
            .assertTrue(cart.hasMiniCartProduct(product), "Товары в мини-корзине не соответствуют товарам в корзине"));
        softAssert.assertAll();
    }

    private int getItemCost(SelenideElement miniCartItem) {
        Matcher matcher = RegexMatcher
            .getRegexMatcher(RegexPatterns.ITEM_PRICE.toString(), miniCartItem.$x(ITEM_PRICE).getText());
        String formattedPrice = StringUtils
            .replace(matcher.group(NumberUtils.INTEGER_ONE), StringUtils.SPACE, StringUtils.EMPTY);
        return Integer.parseInt(formattedPrice);
    }
}
