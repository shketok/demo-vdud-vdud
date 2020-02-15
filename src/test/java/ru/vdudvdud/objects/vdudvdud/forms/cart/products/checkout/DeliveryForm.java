package ru.vdudvdud.objects.vdudvdud.forms.cart.products.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.CountrySelect;
import ru.vdudvdud.testdata.enums.urls.RegionSelect;

public class DeliveryForm extends BasePage {
    /**
     * Локаторы и элементы по работе с основным блоком формы
     */
    private static SelenideElement MAIN_ELEMENT;
    private static final String DELIVERY_FORM_LOC = "#wa-step-region-section";

    /**
     * Локаторы по получению заголовочных элемнетов до полей ввода. В т.ч. данные аккаунта
     */
    private final SelenideElement SECTION_HEADER_TITLE = MAIN_ELEMENT.$("*.wa-header");

    /**
     * Текст описывающий легенду для обязательных полей
     */
    private final SelenideElement LEGEND_STAR_REQUIRED_LOC = MAIN_ELEMENT.$("div.wa-required-text");

    /**
     * Элементы и паттерны локаторов для ввода информации о зоне доставки.
     * Регион для России выбирается через select, а для остальных стран вводится вручную
     */
    private static final String INPUT_LABEL_TEXT_REQUIRED_LOC = "span.wa-required";
    private final SelenideElement INPUT_CITY = MAIN_ELEMENT.$("input.wa-input.js-city-field");
    private final SelenideElement INPUT_REGION = MAIN_ELEMENT.$("input.wa-input.js-region-field");
    private final SelenideElement SELECT_COUNTRY = MAIN_ELEMENT.$("select.wa-select.js-country-field");
    private final SelenideElement SELECT_REGION = MAIN_ELEMENT.$("select.wa-select.js-region-field");
    private final SelenideElement INPUT_CITY_REQUIRED = INPUT_CITY.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement INPUT_REGION_REQUIRED = INPUT_REGION.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement SELECT_COUNTRY_REQUIRED = SELECT_COUNTRY.$(INPUT_LABEL_TEXT_REQUIRED_LOC);
    private final SelenideElement SELECT_REGION_REQUIRED = SELECT_REGION.$(INPUT_LABEL_TEXT_REQUIRED_LOC);

    public DeliveryForm(SelenideElement parentMainElement) {
        MAIN_ELEMENT = parentMainElement.$(DELIVERY_FORM_LOC);
    }

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }


    public void checkThatSectionHeaderTitleInState(Condition condition) {
        SECTION_HEADER_TITLE.shouldBe(condition);
    }

    public void checkThatLegendStarRequiredLocInState(Condition condition) {
        LEGEND_STAR_REQUIRED_LOC.shouldBe(condition);
    }

    public void checkThatInputCityInState(Condition condition) {
        INPUT_CITY.shouldBe(condition);
    }

    public void checkThatInputRegionInState(Condition condition) {
        INPUT_REGION.shouldBe(condition);
    }

    public void checkThatSelectCountryInState(Condition condition) {
        SELECT_COUNTRY.shouldBe(condition);
    }

    public void checkThatSelectRegionInState(Condition condition) {
        SELECT_REGION.shouldBe(condition);
    }

    public void checkThatInputCityRequiredInState(Condition condition) {
        INPUT_CITY_REQUIRED.shouldBe(condition);
    }

    public void checkThatInputRegionRequiredInState(Condition condition) {
        INPUT_REGION_REQUIRED.shouldBe(condition);
    }

    public void checkThatSelectCountryRequiredInState(Condition condition) {
        SELECT_COUNTRY_REQUIRED.shouldBe(condition);
    }

    public void checkThatSelectRegionRequiredInState(Condition condition) {
        SELECT_REGION_REQUIRED.shouldBe(condition);
    }

    public void clickSelectCountry() {
        SELECT_COUNTRY.click();
    }

    public void clickSelectRegion() {
        SELECT_REGION.click();
    }

    public String getInputCityRequiredText() {
        return INPUT_CITY_REQUIRED.getText();
    }

    public String getInputRegionRequiredText() {
        return INPUT_REGION_REQUIRED.getText();
    }

    public String getSelectCountryRequiredText() {
        return SELECT_COUNTRY_REQUIRED.getText();
    }

    public String getSelectRegionRequiredText() {
        return SELECT_REGION_REQUIRED.getText();
    }

    public void fillInputCity(String value) {
        INPUT_CITY.setValue(value);
    }

    public void fillInputRegion(String value) {
        INPUT_REGION.setValue(value);
    }

    public void selectOptionCountryByValue(CountrySelect countrySelect) {
        SELECT_COUNTRY.selectOptionByValue(countrySelect.getAlias());;
    }

    public void selectOptionRegionByValue(RegionSelect regionSelect) {
        SELECT_REGION.selectOptionByValue(regionSelect.getAlias());
    }
}