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
import ru.vdudvdud.testdata.models.essences.MinicartProduct;
import ru.vdudvdud.testdata.objects.Cart;
import ru.vdudvdud.testdata.utils.RegexMatcher;

public class MinicartForm extends PageObject {

    private static final String MAIN_ELEMENT_LOC = "div[class='mini-cart']";
    private static final String ITEM_PRICE_PATTERN = "X\\s([\\d\\s]+)";
    public ElementsCollection MINI_CART_ITEMS = getMainElement().$$x(".//li[@class='mini-cart__item']");
    private final String ITEM_NAME = ".//a[@class='mini-cart__title']";
    private final String ITEM_IMG_LINK = ".//a[@class='mini-cart__pic'] //img";
    private final String ITEM_CURRENCY = ".//div[@class='mini-cart__price'] //span[@class='ruble']";
    private final String ITEM_COUNT = ".//span[@class='mini-cart__quantity']";
    private final String ITEM_PRICE = ".//div[@class='mini-cart__price']";

    private final Cart cart = Cart.getInstance();

    public MinicartForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    public List<MinicartProduct> getProducts() {
        return MINI_CART_ITEMS.stream().map(item -> {
            MinicartProduct minicartProduct = new MinicartProduct();
            minicartProduct.setName(item.$x(ITEM_NAME).getText());
            minicartProduct.setCost(getItemCost(item));
            minicartProduct.setImgLink(item.$x(ITEM_IMG_LINK).getAttribute("src"));
            minicartProduct.setCurrency(item.$x(ITEM_CURRENCY).getText());
            minicartProduct.setCount(Integer.parseInt(item.$x(ITEM_COUNT).getText()));
            return minicartProduct;
        }).collect(Collectors.toList());
    }


    public void checkThatMinicartDataIsCorrect() {
        SoftAssert softAssert = new SoftAssert();
        getProducts().forEach(product -> {
            softAssert.assertTrue(cart.hasMinicartProduct(product));
        });
        softAssert.assertAll();
    }

    private int getItemCost(SelenideElement minicartItem) {
        Matcher matcher = RegexMatcher
            .getRegexMatcher(ITEM_PRICE_PATTERN, minicartItem.$x(ITEM_PRICE).getText());
        String formattedPrice = StringUtils
            .replace(matcher.group(NumberUtils.INTEGER_ONE), StringUtils.SPACE, StringUtils.EMPTY);
        return Integer.parseInt(formattedPrice);
    }
}
