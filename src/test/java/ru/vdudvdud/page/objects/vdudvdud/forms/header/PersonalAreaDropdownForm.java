package ru.vdudvdud.page.objects.vdudvdud.forms.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;

import static com.codeborne.selenide.Selenide.$;

public class PersonalAreaDropdownForm extends PageObject {
    private static final String MAIN_ELEMENT_LOC = ".account-submenu";

    private static final String DROPDOWN_PERSONAL_AREA_LINK_LOC_PATTERN = "ul a[href*='%s']";

    private SelenideElement signIn = getMainElement().
            $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC_PATTERN, BaseUrls.SIGN_IN.getUrlPart()));
    private SelenideElement signUp = getMainElement().
            $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC_PATTERN, BaseUrls.SIGN_UP.getUrlPart()));
    private SelenideElement logout = getMainElement().
            $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC_PATTERN, BaseUrls.LOGOUT.getUrlPart()));

    /**
     * Конструктор основного элемента.
     */
    public PersonalAreaDropdownForm() {
        super($(MAIN_ELEMENT_LOC));
    }

    public void clickSignIn() {
        signIn.click();
    }

    public void clickSignUp() {
        signUp.click();
    }

    public void checkThatLogoutBtnInState(Condition condition) {
        logout.shouldBe(condition);
    }

    public void checkThatSignInBtnInState(Condition condition) {
        signIn.shouldBe(condition);
    }

    public void clickLogout() {
        logout.click();
    }
}
