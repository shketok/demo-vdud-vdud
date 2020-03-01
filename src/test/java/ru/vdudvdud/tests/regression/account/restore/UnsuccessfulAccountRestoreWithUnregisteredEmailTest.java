package ru.vdudvdud.tests.regression.account.restore;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.RestorePasswordSteps;
import ru.vdudvdud.steps.SignInSteps;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class UnsuccessfulAccountRestoreWithUnregisteredEmailTest extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInPage = new SignInSteps();
    private RestorePasswordSteps restorePasswordSteps = new RestorePasswordSteps();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();

        LOG.info("1. Открытие главной страницы");
        mainPageSteps.openMainPage();

        LOG.info("2. Переход на страницу логина");
        headerSteps.goToLoginPage();

        LOG.info("3. Переход на страницу восстановления пароля");
        signInPage.checkThatMainElementsOfThePageAreVisible();
        signInPage.clickToForgotPassword();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-15")
    public void runTest() {
        LOG.info("1. Ввод email на которого не зарегистрирован ни один пользователь");
        restorePasswordSteps.checkThatMainElementsOfThePageAreVisible();
        restorePasswordSteps.restorePassword(user);

        LOG.info("2. Проверка что вывелось сообщение о том что нет пользователя с таким email");
        restorePasswordSteps.checkIncorrentEmailMessageIsVisible();
    }

}
