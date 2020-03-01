package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.enums.urls.SiteUrls;

import static com.codeborne.selenide.Selenide.$;

public class BuyerForm extends PageObject {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static final String BUYER_FORM_LOC = "*#wa-step-auth-section";

    /**
     * Паттерны для полей ввода и ссылок на странице
     */
    private static final String INPUT_FIELD_PATTERN = "input.wa-input[name*='%s']";
    private static final String LINK_PATTERN = "a[href*='%s']";

    /**
     * Локаторы для работы с заголовками полей ввода данных о пользователе
     */
    private static final String INPUT_LABEL_TEXT_LOC = ".//preceding-sibling::div[@class='wa-label']";
    private static final String INPUT_LABEL_TEXT_REQUIRED_LOC = "span.wa-required";

    /**
     * Локаторы по получению заголовочных элемнетов до полей ввода. В т.ч. данные аккаунта
     */
    private SelenideElement sectionHeaderTitle = getMainElement().$("*.wa-header");
    private SelenideElement sectionHeaderFullName = getMainElement().$("*.wa-contact-name");
    private SelenideElement sectionHeaderLogout = getMainElement().$(String.format(LINK_PATTERN, BaseUrls.LOGOUT.getUrlPart()));

    /**
     * Элементы для работы с полями ввода данных о пользователе и элементы
     */
    private SelenideElement firstNameInput = getMainElement().$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.FIRST_NAME.getAlias()));
    private SelenideElement lastNameInput = getMainElement().$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.LAST_NAME.getAlias()));
    private SelenideElement phoneInput = getMainElement().$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.PHONE.getAlias()));
    private SelenideElement emailInput = getMainElement().$(String.format(INPUT_FIELD_PATTERN, BuyerInputAlias.EMAIL.getAlias()));

    /**
     * Элементы для работы с заголовками полей ввода данных о пользователе
     */
    private SelenideElement firstNameInputTitle = firstNameInput.$x(INPUT_LABEL_TEXT_LOC);
    private SelenideElement lastNameInputTitle = lastNameInput.$x(INPUT_LABEL_TEXT_LOC);
    private SelenideElement phoneInputTitle = phoneInput.$x(INPUT_LABEL_TEXT_LOC);
    private SelenideElement emailInputTitle = emailInput.$x(INPUT_LABEL_TEXT_LOC);
    private SelenideElement firstNameInputTitleRequired = firstNameInputTitle.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement lastNameInputTitleRequired = lastNameInputTitle.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement phoneInputTitleRequired = phoneInputTitle.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement emailInputTitleRequired = emailInputTitle.$(INPUT_LABEL_TEXT_REQUIRED_LOC);

    /**
     * Локаторы и элементы для получения информации о обработки персональных данных
     */
    private SelenideElement agreementText = getMainElement().$("div.wa-agreement-wrapper");
    private SelenideElement agreementTextLink = agreementText.$(String.format(LINK_PATTERN, SiteUrls.PRIVACY.getUrlPart()));

    /**
     * Конструктор основного элемента.
     */
    public BuyerForm(SelenideElement parentMainElement) {
        super(parentMainElement.$(BUYER_FORM_LOC));
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
        return $(firstNameInput).getText();
    }

    public String getLastNameText() {
        return $(lastNameInput).getText();
    }

    public String getPhoneText() {
        return $(phoneInput).getText();
    }

    public String getEmailText() {
        return $(emailInput).getText();
    }

    public void fillFirstName(String value) {
        $(firstNameInput).setValue(value);
    }

    public void fillLastName(String value) {
        $(lastNameInput).setValue(value);
    }

    public void fillPhone(String value) {
        $(phoneInput).setValue(value);
    }

    public void fillEmail(String value) {
        $(emailInput).setValue(value);
    }

    public String getFirstNameInputTitleText() {
        return firstNameInputTitle.getText();
    }

    public String getLastNameInputTitleText() {
        return lastNameInputTitle.getText();
    }

    public String getPhoneInputTitleText() {
        return phoneInputTitle.getText();
    }

    public String getEmailInputTitleText() {
        return emailInputTitle.getText();
    }

    public String getSectionHeaderTitleText() {
        return sectionHeaderTitle.getText();
    }

    public String getSectionHeaderFullNameText() {
        return sectionHeaderFullName.getText();
    }

    public String getSectionHeaderLogoutText() {
        return sectionHeaderLogout.getText();
    }

    public void clickSectionHeaderLogout() {
        sectionHeaderLogout.click();
    }

    public void clickAgreementTextLink() {
        agreementTextLink.click();
    }

    public void checkThatSectionHeaderTitleInState(Condition condition) {
        sectionHeaderTitle.shouldBe(condition);
    }

    public void checkThatSectionHeaderFullNameInState(Condition condition) {
        sectionHeaderFullName.shouldBe(condition);
    }

    public void checkThatSectionHeaderLogoutInState(Condition condition) {
        sectionHeaderLogout.shouldBe(condition);
    }

    public void checkThatFirstNameInputInState(Condition condition) {
        firstNameInput.shouldBe(condition);
    }

    public void checkThatLastNameInputInState(Condition condition) {
        lastNameInput.shouldBe(condition);
    }

    public void checkThatPhoneInputInState(Condition condition) {
        phoneInput.shouldBe(condition);
    }

    public void checkThatEmailInputInState(Condition condition) {
        emailInput.shouldBe(condition);
    }

    public void checkThatFirstNameInputTitleRequiredInState(Condition condition) {
        firstNameInputTitleRequired.shouldBe(condition);
    }

    public void checkThatLastNameInputTitleRequiredInState(Condition condition) {
        lastNameInputTitleRequired.shouldBe(condition);
    }

    public void checkThatPhoneInputTitleRequiredInState(Condition condition) {
        phoneInputTitleRequired.shouldBe(condition);
    }

    public void checkThatEmailInputTitleRequiredInState(Condition condition) {
        emailInputTitleRequired.shouldBe(condition);
    }

    public void checkThatAgreementTextInState(Condition condition) {
        agreementText.shouldBe(condition);
    }

    public void checkThatAgreementTextLinkInState(Condition condition) {
        agreementTextLink.shouldBe(condition);
    }

    public void checkThatFirstNameInputTitleInState(Condition condition) {
        firstNameInputTitle.shouldBe(condition);
    }

    public void checkThatLastNameInputTitleInState(Condition condition) {
        lastNameInputTitle.shouldBe(condition);
    }

    public void checkThatPhoneInputTitleInState(Condition condition) {
        phoneInputTitle.shouldBe(condition);
    }

    public void checkThatEmailInputTitleInState(Condition condition) {
        emailInputTitle.shouldBe(condition);
    }
}
