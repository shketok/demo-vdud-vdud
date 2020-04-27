package ru.vdudvdud.tests.regression.account.restore;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.driver.DriverContainer;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.PageNotFound404Steps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.steps.vdudvdud.RestorePasswordAfterEmailVerifySteps;
import ru.vdudvdud.steps.vdudvdud.RestorePasswordSteps;
import ru.vdudvdud.steps.vdudvdud.SignInSteps;
import ru.vdudvdud.steps.yopmail.YopmailSteps;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UnsuccessfulPasswordRestoreMoreThenOneTime extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInSteps = new SignInSteps();
    private RestorePasswordSteps restorePasswordSteps = new RestorePasswordSteps();
    private YopmailSteps yopmailSteps = new YopmailSteps();
    private RestorePasswordAfterEmailVerifySteps restorePasswordAfterEmailVerifySteps = new RestorePasswordAfterEmailVerifySteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();
    private PageNotFound404Steps pageNotFound404Steps = new PageNotFound404Steps();
    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();

        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("2. Заполнение формы регистрации");
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("3. Проверка успешности регистрации");
        mainPageSteps.checkThatMainElementsOfThePageAreVisible();
        mainPageSteps.checkThatMainPageIsOpen();
        headerSteps.checkLogoutVisible();

        LOG.info("4. Выход из аккаунта и проверка, что мы вышли из аккаунта");
        headerSteps.logout();
        headerSteps.checkLogoutInvisible();
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-55")
    public void runTest() {
        LOG.info("1. Открытие главной страницы и переход на страницу логина");
        mainPageSteps.openMainPage();
        headerSteps.goToLoginPage();

        LOG.info("2. Переход на страницу восстановления пароля");
        signInSteps.checkThatMainElementsOfThePageAreVisible();
        signInSteps.clickToForgotPassword();

        LOG.info("3. Ввостановление пароля");
        restorePasswordSteps.checkThatMainElementsOfThePageAreVisible();
        restorePasswordSteps.restorePassword(user);

        DriverContainer.switchToSecond();

        LOG.info("4. Открытие yopmail, вход и открытие ссылки восстановления пароля");
        yopmailSteps.openYopmail();
        yopmailSteps.navigateToUserInbox(user);
        String restorePasswordUrl = yopmailSteps.getLinkForRestorePassword();

        DriverContainer.switchToFirst();
        DriverContainer.getDriver().navigate().to(restorePasswordUrl);

        LOG.info("5. Ввод и подтверждение нового пароля");
        restorePasswordAfterEmailVerifySteps.checkThatMainElementsOfThePageAreVisible();
        String newPassword = TestDataProvider.generateRandomStringWithBaseLength();
        user.setPassword(newPassword);
        restorePasswordAfterEmailVerifySteps.fillPassword(newPassword);
        restorePasswordAfterEmailVerifySteps.fillRepeatPassword(newPassword);
        restorePasswordAfterEmailVerifySteps.clickConfirm();
        mainPageSteps.checkThatMainElementsOfThePageAreVisible();

        DriverContainer.getDriver().navigate().to(restorePasswordUrl);
        pageNotFound404Steps.checkThatMainElementsOfThePageAreVisible();
    }


}
