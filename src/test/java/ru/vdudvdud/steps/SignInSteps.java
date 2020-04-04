package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.page.objects.vdudvdud.pages.SignInPage;
import ru.vdudvdud.testdata.models.essences.User;

public class SignInSteps extends BaseSteps {
    private SignInPage signInPage = new SignInPage();

    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        signInPage.checkThatLoginInState(Condition.visible);
        signInPage.checkThatPasswordInState(Condition.visible);
        signInPage.checkThatTitleInState(Condition.visible);
        signInPage.checkThatConfirmInState(Condition.visible);
        signInPage.checkThatForgotPasswordInState(Condition.visible);
        signInPage.checkThatRegistrationInState(Condition.visible);
    }

    @Step("Ввод данных для входа в аккаунта и подтверждение входа в аккаунт")
    public void signIn(User user) {
        signInPage.fillLogin(user.getEmail());
        signInPage.fillPassword(user.getPassword());
        signInPage.clickConfirm();
    }

    @Step("Переход на страницу восстановления пароля")
    public void clickToForgotPassword() {
        signInPage.clickForgotPassword();
    }


    @Step("Проверка видимости поля с ошибкой, когда логин остутствует")
    public void checkThatMandatoryLoginErrorMessageIsVisible(){
        signInPage.checkThatMandatoryLoginErrorMsgInState();
    }

    @Step("Проверка видимости поля с ошибкой, когда пароль остутствует")
    public void checkThatMandatoryPasswordErrorMessageIsVisible(){
        signInPage.checkThatMandatoryPasswordErrorMsgInState();
    }

    @Step("Проверка видимости поля с ошибкой с некорретным паролем или почтой")
    public void checkThatEitherPasswordOrEmailIncorrectMessageIsVisible(){
        signInPage.checkThatEitherPasswordOrEmailIncorrectMessageInState();
    }
}
