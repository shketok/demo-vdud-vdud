package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.objects.vdudvdud.pages.RestorePasswordPage;
import ru.vdudvdud.testdata.models.essences.User;

public class RestorePasswordSteps extends BaseSteps {

    private RestorePasswordPage restorePasswordPage = new RestorePasswordPage();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        restorePasswordPage.checkThatLoginInState(Condition.visible);
        restorePasswordPage.checkThatConfirmInState(Condition.visible);
    }

    @Step("Ввод данных для восстановления пароля и подтерждение восстановления")
    public void restorePassword(User user) {
        restorePasswordPage.fillLogin(user.getEmail());
        restorePasswordPage.checkThatEmailFieldFilledWithUserEmail(user);
        restorePasswordPage.clickConfirm();
    }

    @Step("Ожидание, присутсвия сообщения о некорректно введенном email")
    public void checkIncorrentEmailMessageIsVisible() {
        restorePasswordPage.checkIncorrectEmailMessageIsVisible();
    }
}
