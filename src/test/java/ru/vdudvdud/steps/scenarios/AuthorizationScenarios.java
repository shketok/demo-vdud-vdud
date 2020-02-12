package ru.vdudvdud.steps.scenarios;

import io.qameta.allure.Step;
import ru.vdudvdud.adaptors.selenide.utils.Logger;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.PersonalRoomSteps;
import ru.vdudvdud.steps.SignInSteps;
import ru.vdudvdud.testdata.models.essences.User;


/**
 * Сценарии авторизации пользователей на сайте
 */
public class AuthorizationScenarios {
    protected static final Logger LOG = Logger.getInstance();

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInSteps = new SignInSteps();
    private PersonalRoomSteps personalRoomSteps = new PersonalRoomSteps();

    @Step("Выполнение успешной авторизации зарегестрированным пользователем")
    public void authorize(User user) {
        LOG.info("Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("Вход в аккаунт через форму входа");
        headerSteps.goToLoginPage();
        signInSteps.checkThatMainElementsOfThePageAreVisible();
        signInSteps.signIn(user);

        LOG.info("Проверка, что вход в аккаунт был произведен успешно");
        personalRoomSteps.checkThatMainElementsOfThePageAreVisible();
    }
}
