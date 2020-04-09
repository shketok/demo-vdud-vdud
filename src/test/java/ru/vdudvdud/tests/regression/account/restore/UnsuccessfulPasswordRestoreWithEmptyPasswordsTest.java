package ru.vdudvdud.tests.regression.account.restore;

import io.qameta.allure.Link;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.driver.DriverContainer;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.steps.vdudvdud.RestorePasswordAfterEmailVerifySteps;
import ru.vdudvdud.steps.vdudvdud.RestorePasswordSteps;
import ru.vdudvdud.steps.vdudvdud.SignInSteps;
import ru.vdudvdud.steps.yopmail.YopmailSteps;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class UnsuccessfulPasswordRestoreWithEmptyPasswordsTest extends BaseTest {


    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInSteps = new SignInSteps();
    private RestorePasswordSteps restorePasswordSteps = new RestorePasswordSteps();
    private YopmailSteps yopmailSteps = new YopmailSteps();
    private RestorePasswordAfterEmailVerifySteps restorePasswordAfterEmailVerifySteps = new RestorePasswordAfterEmailVerifySteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();
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

        LOG.info("5. Переход на страницу логина");
        headerSteps.goToLoginPage();
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-54")
    public void runTest() {

        LOG.info("1. Переход на страницу восстановления пароля");
        signInSteps.checkThatMainElementsOfThePageAreVisible();
        signInSteps.clickToForgotPassword();

        LOG.info("2. Ввостановление пароля");
        restorePasswordSteps.checkThatMainElementsOfThePageAreVisible();
        restorePasswordSteps.restorePassword(user);

        DriverContainer.switchToSecond();

        LOG.info("3. Открытие yopmail, вход и открытие ссылки восстановления пароля");
        yopmailSteps.openYopmail();
        yopmailSteps.navigateToUserInbox(user);
        String restorePasswordUrl = yopmailSteps.getLinkForRestorePassword();

        DriverContainer.switchToFirst();
        DriverContainer.getDriver().navigate().to(restorePasswordUrl);

        LOG.info("4. Ввод и подтверждение нового пароля");
        restorePasswordAfterEmailVerifySteps.checkThatMainElementsOfThePageAreVisible();
        restorePasswordAfterEmailVerifySteps.fillPassword(StringUtils.EMPTY);
        restorePasswordAfterEmailVerifySteps.fillRepeatPassword(StringUtils.EMPTY);
        restorePasswordAfterEmailVerifySteps.clickConfirm();

        LOG.info("5. Проверка того, что вывелось сообщение об ошибке");
        restorePasswordAfterEmailVerifySteps.checkThatEmptyPasswordsErrorMsgIsVisible();
    }
}
