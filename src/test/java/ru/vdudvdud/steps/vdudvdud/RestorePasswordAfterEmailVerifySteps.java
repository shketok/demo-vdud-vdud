package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.objects.vdudvdud.pages.RestorePasswordConfirmationPage;

public class RestorePasswordAfterEmailVerifySteps extends BaseSteps {

    private RestorePasswordConfirmationPage restorePasswordConfirmationPage = new RestorePasswordConfirmationPage();


    @Override
    public void checkThatMainElementsOfThePageAreVisible() {
        restorePasswordConfirmationPage.checkThatPasswordInState(Condition.visible);
        restorePasswordConfirmationPage.checkThatRepeatPasswordInState(Condition.visible);
        restorePasswordConfirmationPage.checkThatConfirmInState(Condition.visible);
    }

    @Step("Проверка того что вывелось сообщение о несовпадении паролей")
    public void checkThatDifferentPasswordsErrorMsgIsVisible() {
        restorePasswordConfirmationPage.checkThatDifferentPasswordsErrorMsgIsVisible();
    }

    @Step("Проверка того что вывелось сообщение о вводе пустого пароля")
    public void checkThatEmptyPasswordsErrorMsgIsVisible() {
        restorePasswordConfirmationPage.checkThatEmptyPasswordsErrorMsgIsVisible();
    }

    @Step("Заполнение нового пароля")
    public void fillPassword(String password) {
        restorePasswordConfirmationPage.fillPassword(password);
    }

    @Step("Заполнение подтверждения пароля")
    public void fillRepeatPassword(String repeatPassword) {
        restorePasswordConfirmationPage.fillRepeatPassword(repeatPassword);
    }

    @Step("Подтверждение нового пароля. Отправка формы")
    public void clickConfirm() {
        restorePasswordConfirmationPage.clickConfirm();
    }


}
