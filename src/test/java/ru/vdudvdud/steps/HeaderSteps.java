package ru.vdudvdud.steps;

import io.qameta.allure.Step;
import ru.vdudvdud.testdata.objects.vdudvdud.pages.HeaderPage;
import ru.vdudvdud.testdata.objects.vdudvdud.forms.PersonalAreaDropdownForm;

public class HeaderSteps {
    private HeaderPage headerPage;
    private PersonalAreaDropdownForm personalAreaDropdownForm;


    public HeaderSteps() {
        headerPage = new HeaderPage();
        personalAreaDropdownForm = headerPage.getPersonalAreaDropDownForm();
    }

    @Step("Открытие формы регистрации сайта через верхнее меню")
    public void openSignUp() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.clickSignUp();

    }

    @Step("Проверка, что видна кнопка выхода из аккаунта")
    public void checkLogoutVisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnVisible();
    }

    @Step("Проверка, что не видна кнопка выхода из аккаунта")
    public void checkLogoutInvisible() {
        headerPage.hoverPersonalArea();
        personalAreaDropdownForm.checkThatLogoutBtnInvisible();
    }
}
