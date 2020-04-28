package ru.vdudvdud.steps.vdudvdud.scenarios;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ru.vdudvdud.adaptors.selenide.utils.Logger;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.testdata.models.essences.User;


/**
 * Сценарии по регистрации пользователей на сайте
 */
public class RegistrationScenarios {
    protected static final Logger LOG = Logger.getInstance();

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();

    @Step("Сценарий регистрации пользователя на сайте")
    public void registration(User user) {
        LOG.info("Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("Заполнение формы регистрации");
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("Проверка успешности регистрации");
        mainPageSteps.checkThatMainPageIsOpen();
        mainPageSteps.checkThatMainElementsOfThePageAreVisible();
        headerSteps.checkLogoutVisible();
    }

    @Step("Сценарий регистрации пользователя на сайте и выхода из зарегистрированного аккаунта")
    public void registrationAndLogout(User user) {
        LOG.info("Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("Заполнение формы регистрации");
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("Проверка успешности регистрации");
        mainPageSteps.checkThatMainElementsOfThePageAreVisible();
        mainPageSteps.checkThatMainPageIsOpen();
        headerSteps.checkLogoutVisible();

        LOG.info("Выход из аккаунта и проверка, что мы вышли из аккаунта");
        headerSteps.logout();
        headerSteps.checkLogoutInvisible();
    }

}