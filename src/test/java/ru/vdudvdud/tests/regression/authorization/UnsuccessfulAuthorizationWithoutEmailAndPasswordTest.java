package ru.vdudvdud.tests.regression.authorization;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.SignInSteps;
import ru.vdudvdud.testdata.builders.UsersCreator;
import ru.vdudvdud.testdata.enums.UserAliases;
import ru.vdudvdud.testdata.models.essences.User;

public class UnsuccessfulAuthorizationWithoutEmailAndPasswordTest extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInSteps = new SignInSteps();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createUser(UserAliases.USER_EMPTY_PASSWORD_AND_EMAIL);
    }

    @Test
    public void runTest() {
        LOG.info("1. Открытие главной страницы и формы логина");
        mainPageSteps.openMainPage();
        headerSteps.goToLoginPage();
        signInSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("2. Попытка входа с некорректной почтой и паролем");
        signInSteps.signIn(user);

        LOG.info("3. Проверка, что отобразились сообщения сигнализирующие необходимость заполенения обязательных полей");
        signInSteps.checkThatMandatoryLoginErrorMessageIsVisible();
        signInSteps.checkThatMandatoryPasswordErrorMessageIsVisible();
    }
}
