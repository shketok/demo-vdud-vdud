package ru.vdudvdud.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.enums.urls.SiteUrls;

import static com.codeborne.selenide.Selenide.$;

public class BuyerForm extends BasePage {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static SelenideElement MAIN_ELEMENT;
    private static final String BUYER_FORM_LOC = "*#wa-step-auth-section";

    /**
     * Паттерны для полей ввода и ссылок на странице
     */
    private static final String INPUT_FIELD_PATTERN = "input.wa-input[name*='%s']";
    private static final String LINK_PATTERN = "a[href*='%s']";

    /**
     * Локаторы по получению заголовочных элемнетов до полей ввода. В т.ч. данные аккаунта
     */
    private final SelenideElement SECTION_HEADER_TITLE = MAIN_ELEMENT.$("*.wa-header");
    private final SelenideElement SECTION_HEADER_FULL_NAME = MAIN_ELEMENT.$("*.wa-contact-name");
    private final SelenideElement SECTION_HEADER_LOGOUT = MAIN_ELEMENT.$(String.format(LINK_PATTERN, BaseUrls.LOGOUT.getUrlPart()));

    /**
     * Элементы для работы с полями ввода данных о пользователе и элементы
     */
    private final SelenideElement FIRST_NAME_INPUT = MAIN_ELEMENT.$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.FIRST_NAME.getAlias()));
    private final SelenideElement LAST_NAME_INPUT = MAIN_ELEMENT.$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.LAST_NAME.getAlias()));
    private final SelenideElement PHONE_INPUT = MAIN_ELEMENT.$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.PHONE.getAlias()));
    private final SelenideElement EMAIL_INPUT = MAIN_ELEMENT.$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.EMAIL.getAlias()));

    /**
     * Локаторы и элементы для работы с заголовками полей ввода данных о пользователе
     */
    private static final String INPUT_LABEL_TEXT_LOC = "//preceding-sibling::div[@class='wa-label']";
    private static final String INPUT_LABEL_TEXT_REQUIRED_LOC = "span.wa-required";
    private final SelenideElement FIRST_NAME_INPUT_TITLE = FIRST_NAME_INPUT.$x(INPUT_LABEL_TEXT_LOC);
    private final SelenideElement LAST_NAME_INPUT_TITLE = LAST_NAME_INPUT.$x(INPUT_LABEL_TEXT_LOC);
    private final SelenideElement PHONE_INPUT_TITLE = PHONE_INPUT.$x(INPUT_LABEL_TEXT_LOC);
    private final SelenideElement EMAIL_INPUT_TITLE = EMAIL_INPUT.$x(INPUT_LABEL_TEXT_LOC);
    private final SelenideElement FIRST_NAME_INPUT_TITLE_REQUIRED = FIRST_NAME_INPUT_TITLE.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement LAST_NAME_INPUT_TITLE_REQUIRED = LAST_NAME_INPUT_TITLE.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement PHONE_INPUT_TITLE_REQUIRED = PHONE_INPUT_TITLE.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement EMAIL_INPUT_TITLE_REQUIRED = EMAIL_INPUT_TITLE.$(INPUT_LABEL_TEXT_REQUIRED_LOC);

    /**
     * Локаторы и элементы для получения информации о обработки персональных данных
     */
    private final SelenideElement AGREEMENT_TEXT = MAIN_ELEMENT.$("div.wa-agreement-wrapper");
    private final SelenideElement AGREEMENT_TEXT_LINK = AGREEMENT_TEXT.$(String.format(LINK_PATTERN, SiteUrls.PRIVACY.getUrlPart()));

    public BuyerForm(SelenideElement parentMainElement) {
        MAIN_ELEMENT = parentMainElement.$(BUYER_FORM_LOC);
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }


    private enum BuyerInputAlias {
        FIRST_NAME("firstname"),
        LAST_NAME("lastname"),
        PHONE("phone"),
        EMAIL("email");

        private final String alias;

        BuyerInputAlias(String alias) {
            this.alias = alias;
        }

        public String getAlias() {
            return alias;
        }
    }

    public String getFirstNameText() {
        return $(FIRST_NAME_INPUT).getText();
    }

    public String getLastNameText() {
        return $(LAST_NAME_INPUT).getText();
    }

    public String getPhoneText() {
        return $(PHONE_INPUT).getText();
    }

    public String getEmailText() {
        return $(EMAIL_INPUT).getText();
    }

    public void fillFirstName(String value) {
        $(FIRST_NAME_INPUT).setValue(value);
    }

    public void fillLastName(String value) {
        $(LAST_NAME_INPUT).setValue(value);
    }

    public void fillPhone(String value) {
        $(PHONE_INPUT).setValue(value);
    }

    public void fillEmail(String value) {
        $(EMAIL_INPUT).setValue(value);
    }

    public String getFirstNameInputTitleText() {
        return FIRST_NAME_INPUT_TITLE.getText();
    }

    public String getLastNameInputTitleText() {
        return LAST_NAME_INPUT_TITLE.getText();
    }

    public String getPhoneInputTitleText() {
        return PHONE_INPUT_TITLE.getText();
    }

    public String getEmailInputTitleText() {
        return EMAIL_INPUT_TITLE.getText();
    }

    public String getSectionHeaderTitleText() {
        return SECTION_HEADER_TITLE.getText();
    }

    public String getSectionHeaderFullNameText() {
        return SECTION_HEADER_FULL_NAME.getText();
    }

    public String getSectionHeaderLogoutText() {
        return SECTION_HEADER_LOGOUT.getText();
    }

    public void clickSectionHeaderLogout() {
        SECTION_HEADER_LOGOUT.click();
    }

    public void clickAgreementTextLink() {
        AGREEMENT_TEXT_LINK.click();
    }

    public void checkThatSectionHeaderTitleInState(Condition condition) {
        SECTION_HEADER_TITLE.shouldBe(condition);
    }

    public void checkThatSectionHeaderFullNameInState(Condition condition) {
        SECTION_HEADER_FULL_NAME.shouldBe(condition);
    }

    public void checkThatSectionHeaderLogoutInState(Condition condition) {
        SECTION_HEADER_LOGOUT.shouldBe(condition);
    }

    public void checkThatFirstNameInputInState(Condition condition) {
        FIRST_NAME_INPUT.shouldBe(condition);
    }

    public void checkThatLastNameInputInState(Condition condition) {
        LAST_NAME_INPUT.shouldBe(condition);
    }

    public void checkThatPhoneInputInState(Condition condition) {
        PHONE_INPUT.shouldBe(condition);
    }

    public void checkThatEmailInputInState(Condition condition) {
        EMAIL_INPUT.shouldBe(condition);
    }

    public void checkThatFirstNameInputTitleRequiredInState(Condition condition) {
        FIRST_NAME_INPUT_TITLE_REQUIRED.shouldBe(condition);
    }

    public void checkThatLastNameInputTitleRequiredInState(Condition condition) {
        LAST_NAME_INPUT_TITLE_REQUIRED.shouldBe(condition);
    }

    public void checkThatPhoneInputTitleRequiredInState(Condition condition) {
        PHONE_INPUT_TITLE_REQUIRED.shouldBe(condition);
    }

    public void checkThatEmailInputTitleRequiredInState(Condition condition) {
        EMAIL_INPUT_TITLE_REQUIRED.shouldBe(condition);
    }

    public void checkThatAgreementTextInState(Condition condition) {
        AGREEMENT_TEXT.shouldBe(condition);
    }

    public void checkThatAgreementTextLinkInState(Condition condition) {
        AGREEMENT_TEXT_LINK.shouldBe(condition);
    }

    public void checkThatFirstNameInputTitleInState(Condition condition) {
        FIRST_NAME_INPUT_TITLE.shouldBe(condition);
    }

    public void checkThatLastNameInputTitleInState(Condition condition) {
        LAST_NAME_INPUT_TITLE.shouldBe(condition);
    }

    public void checkThatPhoneInputTitleInState(Condition condition) {
        PHONE_INPUT_TITLE.shouldBe(condition);
    }

    public void checkThatEmailInputTitleInState(Condition condition) {
        EMAIL_INPUT_TITLE.shouldBe(condition);
    }
}
