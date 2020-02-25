package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import ru.vdudvdud.objects.vdudvdud.pages.RestorePasswordAfterEmailVerifyPage;

public class RestorePasswordAfterEmailVerifySteps extends BaseSteps {

    private RestorePasswordAfterEmailVerifyPage restorePasswordAfterEmailVerifyPage = new RestorePasswordAfterEmailVerifyPage();


    @Override
    public void checkThatMainElementsOfThePageAreVisible() {
        restorePasswordAfterEmailVerifyPage.checkThatPasswordInState(Condition.visible);
        restorePasswordAfterEmailVerifyPage.checkThatRepeatPasswordInState(Condition.visible);
        restorePasswordAfterEmailVerifyPage.checkThatConfirmInState(Condition.visible);
    }

    public void fillPassword(String password) {
        restorePasswordAfterEmailVerifyPage.fillPassword(password);
    }

    public void fillRepeatPassword(String repeatPassword) {
        restorePasswordAfterEmailVerifyPage.fillRepeatPassword(repeatPassword);
    }

    public void clickConfirm() {
        restorePasswordAfterEmailVerifyPage.clickConfirm();
    }

}
