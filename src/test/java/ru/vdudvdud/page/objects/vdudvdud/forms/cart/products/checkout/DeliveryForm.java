package ru.vdudvdud.page.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.CountrySelect;
import ru.vdudvdud.testdata.enums.urls.RegionSelect;

public class DeliveryForm extends PageObject {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static final String DELIVERY_FORM_LOC = "#wa-step-region-section";

    /**
     * Паттерны локатора для ввода информации о зоне доставки.
     */
    private static final String INPUT_LABEL_TEXT_REQUIRED_LOC = "span.wa-required";

    /**
     * Локаторы по получению заголовочных элемнетов до полей ввода. В т.ч. данные аккаунта
     */
    private SelenideElement sectionHeaderTitle = getMainElement().$("*.wa-header");

    /**
     * Текст описывающий легенду для обязательных полей
     */
    private SelenideElement legendStarRequired = getMainElement().$("div.wa-required-text");

    /**
     * Элементы для ввода информации о зоне доставки.
     * Регион для России выбирается через select, а для остальных стран вводится вручную
     */
    private SelenideElement inputCity = getMainElement().$("input.wa-input.js-city-field");
    private SelenideElement inputRegion = getMainElement().$("input.wa-input.js-region-field");
    private SelenideElement selectCountry = getMainElement().$("select.wa-select.js-country-field");
    private SelenideElement selectRegion = getMainElement().$("select.wa-select.js-region-field");
    private SelenideElement inputCityRequired = inputCity.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement inputRegionRequired = inputRegion.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement selectCountryRequired = selectCountry.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private SelenideElement selectRegionRequired = selectRegion.$(INPUT_LABEL_TEXT_REQUIRED_LOC);

    /**
     * Конструктор основного элемента.
     */
    public DeliveryForm(SelenideElement parentMainElement) {
        super(parentMainElement.$(DELIVERY_FORM_LOC));
    }


    public void checkThatSectionHeaderTitleInState(Condition condition) {
        sectionHeaderTitle.shouldBe(condition);
    }

    public void checkThatLegendStarRequiredLocInState(Condition condition) {
        legendStarRequired.shouldBe(condition);
    }

    public void checkThatInputCityInState(Condition condition) {
        inputCity.shouldBe(condition);
    }

    public void checkThatInputRegionInState(Condition condition) {
        inputRegion.shouldBe(condition);
    }

    public void checkThatSelectCountryInState(Condition condition) {
        selectCountry.shouldBe(condition);
    }

    public void checkThatSelectRegionInState(Condition condition) {
        selectRegion.shouldBe(condition);
    }

    public void checkThatInputCityRequiredInState(Condition condition) {
        inputCityRequired.shouldBe(condition);
    }

    public void checkThatInputRegionRequiredInState(Condition condition) {
        inputRegionRequired.shouldBe(condition);
    }

    public void checkThatSelectCountryRequiredInState(Condition condition) {
        selectCountryRequired.shouldBe(condition);
    }

    public void checkThatSelectRegionRequiredInState(Condition condition) {
        selectRegionRequired.shouldBe(condition);
    }

    public void clickSelectCountry() {
        selectCountry.click();
    }

    public void clickSelectRegion() {
        selectRegion.click();
    }

    public String getInputCityRequiredText() {
        return inputCityRequired.getText();
    }

    public String getInputRegionRequiredText() {
        return inputRegionRequired.getText();
    }

    public String getSelectCountryRequiredText() {
        return selectCountryRequired.getText();
    }

    public String getSelectRegionRequiredText() {
        return selectRegionRequired.getText();
    }

    public void fillInputCity(String value) {
        inputCity.setValue(value);
    }

    public void fillInputRegion(String value) {
        inputRegion.setValue(value);
    }

    public void selectOptionCountryByValue(CountrySelect countrySelect) {
        selectCountry.selectOptionByValue(countrySelect.getAlias());;
    }

    public void selectOptionRegionByValue(RegionSelect regionSelect) {
        selectRegion.selectOptionByValue(regionSelect.getAlias());
    }

    public String getSectionHeaderTitleText() {
        return sectionHeaderTitle.getText();
    }

    public String getLegendStarRequiredLocText() {
        return legendStarRequired.getText();
    }
}