package ru.vdudvdud.page.objects.vdudvdud.forms.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;

import static com.codeborne.selenide.Selenide.$;

public class PersonalAreaDropdownForm extends BasePage {
    private static final String MAIN_ELEMENT = ".account-submenu ul";

    private static final String DROPDOWN_PERSONAL_AREA_LINK_LOC = ".account-submenu a[href*='%s']";

    private static final SelenideElement SIGN_IN = $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC, BaseUrls.SIGN_IN.getUrlPart()));
    private static final SelenideElement SIGN_UP = $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC, BaseUrls.SIGN_UP.getUrlPart()));
    private static final SelenideElement LOGOUT = $(String.format(DROPDOWN_PERSONAL_AREA_LINK_LOC, BaseUrls.LOGOUT.getUrlPart()));

    @Override
    protected SelenideElement getMainElement() {
        return $(MAIN_ELEMENT);
    }

    public void clickSignIn() {
        SIGN_IN.click();
    }

    public void clickSignUp() {
        SIGN_UP.click();
    }

    public void checkThatLogoutBtnInState(Condition condition) {
        LOGOUT.shouldBe(condition);
    }

    public void checkThatSignInBtnInState(Condition condition) {
        SIGN_IN.shouldBe(condition);
    }

    public void clickLogout() {
        LOGOUT.click();
    }
}