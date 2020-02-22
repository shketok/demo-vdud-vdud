package ru.vdudvdud.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.objects.vdudvdud.forms.PersonalAreaDropdownForm;
import ru.vdudvdud.testdata.constants.Delimiters;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.utils.RegexMatcher;

import static com.codeborne.selenide.Selenide.$;


/**
 * Класс описывающий шапку, которая присутствует везде, где бы мы не находились на сайте.
 */
public class HeaderPage extends BasePage {
    private static final SelenideElement MAIN_ELEMENT = $("a.logo");

    private static final SelenideElement TOP_MENU = $("div.top-menu");
    private static final SelenideElement PERSONAL_AREA = $("[class*='top-bar'] .info-settings div > .icon-user");
    private static final SelenideElement CURRENCY_TAB = $("div.info-settings div[class*='currency']");
    private static final SelenideElement MAIN_CONTACTS_LABEL = $("div.main-contacts");
    private static final SelenideElement LOGO = $(String.format("a.logo[href='%s']", BaseUrls.BASE.getUrlPart()));
    private static final SelenideElement BASKET = $(String.format("div[class*='store-actions'] a[href*='%s'][class*='store']", BaseUrls.ORDER.getUrlPart()));

    private static final String CART_AMOUNT_LOC = "span[class*='cart-amount']";
    private static final String PRODUCTS_PRICE_LOC = "div[class*='cart-content-text']";

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    public void clickMainLogo() {
        getMainElement().click();
    }

    public PersonalAreaDropdownForm getPersonalAreaDropDownForm() {
        return new PersonalAreaDropdownForm();
    }


    public void hoverPersonalArea() {
        PERSONAL_AREA.shouldBe(Condition.visible).hover();
    }

    public void checkThatTopMenuInState(Condition condition) {
        TOP_MENU.shouldBe(condition);
    }

    public void checkThatPersonalAreaInState(Condition condition) {
        PERSONAL_AREA.shouldBe(condition);
    }

    public void checkThatCurrencyTabInState(Condition condition) {
        CURRENCY_TAB.shouldBe(condition);
    }

    public void checkThatMainContactsLabelInState(Condition condition) {
        MAIN_CONTACTS_LABEL.shouldBe(condition);
    }

    public void checkThatLogoInState(Condition condition) {
        LOGO.shouldBe(condition);
    }

    public void checkThatBasketInState(Condition condition) {
        BASKET.shouldBe(condition);
    }


    public String getCartAmountText() {
        return BASKET.$(CART_AMOUNT_LOC).getText();
    }

    public String getProductCostText() {
        return RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces( BASKET.$(PRODUCTS_PRICE_LOC).getText(),
                RegexPatterns.DIGITS.toString());
    }

    public String getProductCurrencyText() {
        return RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(BASKET.$(PRODUCTS_PRICE_LOC).getText(),
                RegexPatterns.NON_DIGITS.toString());
    }

    public void clickBasket() {
        BASKET.click();
    }
}
