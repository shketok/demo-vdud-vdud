package ru.vdudvdud.page.objects.vdudvdud.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.page.objects.vdudvdud.forms.header.PersonalAreaDropdownForm;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.utils.RegexMatcher;


/**
 * Класс описывающий шапку, которая присутствует везде, где бы мы не находились на сайте.
 */
public class HeaderPage extends BasePage {
    private static final String MAIN_ELEMENT_LOC = ".page-header";

    private SelenideElement topMenu = getMainElement().$("div.top-menu");
    private SelenideElement personalArea = getMainElement().$("[class*='top-bar'] .info-settings div > .icon-user");
    private SelenideElement currencyTab = getMainElement().$("div.info-settings div[class*='currency']");
    private SelenideElement mainContactsLabel = getMainElement().$("div.main-contacts");
    private SelenideElement logo = getMainElement().$(String.format("a.logo[href='%s']", BaseUrls.BASE.getUrlPart()));
    private SelenideElement basket = getMainElement().$(
        String.format("div[class*='store-actions'] a[href*='%s'][class*='store'] div[class='store-actions__cart-icon']", // если убрать div[class='store-actions__cart-icon'], то клик будет проходить по элементу меню тк до этого был произведен hover на него
            BaseUrls.ORDER.getUrlPart())
    );

    private SelenideElement cartAmount = basket.$x("./following-sibling::span[contains(@class, 'cart-amount')]");
    private SelenideElement productsPrice = getMainElement().$("div[class*='cart-content-text']");

    /**
     * Конструктор основного элемента.
     */
    public HeaderPage() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void clickMainLogo() {
        getMainElement().click();
    }

    public PersonalAreaDropdownForm getPersonalAreaDropDownForm() {
        return new PersonalAreaDropdownForm();
    }


    public void hoverPersonalArea() {
        personalArea.shouldBe(Condition.visible).hover();
    }

    public void checkThatTopMenuInState(Condition condition) {
        topMenu.shouldBe(condition);
    }

    public void checkThatPersonalAreaInState(Condition condition) {
        personalArea.shouldBe(condition);
    }

    public void checkThatCurrencyTabInState(Condition condition) {
        currencyTab.shouldBe(condition);
    }

    public void checkThatMainContactsLabelInState(Condition condition) {
        mainContactsLabel.shouldBe(condition);
    }

    public void checkThatLogoInState(Condition condition) {
        logo.shouldBe(condition);
    }

    public void checkThatBasketInState(Condition condition) {
        basket.shouldBe(condition);
    }


    public String getCartAmountText() {
        return cartAmount.getText();
    }

    public String getProductCostText() {
        return RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces( productsPrice.getText(),
                RegexPatterns.DIGITS.toString());
    }

    public String getProductCurrencyText() {
        return RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(productsPrice.getText(),
                RegexPatterns.NON_DIGITS.toString());
    }

    public void clickBasket() {
        basket.click();

    }
}
