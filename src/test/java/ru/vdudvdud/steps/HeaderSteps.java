package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.testdata.objects.vdudvdud.forms.PersonalAreaDropdownForm;
import ru.vdudvdud.testdata.objects.vdudvdud.pages.HeaderPage;

public class HeaderSteps extends BaseSteps {
    private HeaderPage headerPage = new HeaderPage();
    private PersonalAreaDropdownForm personalAreaDropdownForm = headerPage.getPersonalAreaDropDownForm();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        headerPage.checkThatTopMenuInState(Condition.visible);
        headerPage.checkThatPersonalAreaInState(Condition.visible);
        headerPage.checkThatCurrencyTabInState(Condition.visible);
        headerPage.checkThatMainContactsLabelInState(Condition.visible);
        headerPage.checkThatLogoInState(Condition.visible);
        headerPage.checkThatBasketInState(Condition.visible);
    }

    @Step("Открытие формы регистрации сайта через верхнее меню")
    public void openSignUp() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.clickSignUp();
    }

    @Step("Проверка, что видна кнопка выхода из аккаунта")
    public void checkLogoutVisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.visible);
    }

    @Step("Проверка, что не видна кнопка выхода из аккаунта")
    public void checkLogoutInvisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.disappear);
    }

    @Step("Выход из авторизованного аккаунта")
    public void logout() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInState(Condition.visible);
        personalAreaDropdownForm.clickLogout();
    }

    @Step("Переход к форме входа в аккаунт")
    public void goToLoginPage() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatSignInBtnInState(Condition.visible);
        personalAreaDropdownForm.clickSignIn();
    }
}
