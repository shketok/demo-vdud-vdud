package ru.vdudvdud.tests.regression.account.authorization;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.PersonalRoomSteps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.steps.vdudvdud.SignInSteps;
import ru.vdudvdud.testdata.models.essences.User;

public class SuccessfulAuthorizationTest extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();
    private SignInSteps signInSteps = new SignInSteps();
    private PersonalRoomSteps personalRoomSteps = new PersonalRoomSteps();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-10")
    public void runTest() {
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

        LOG.info("5. Вход в аккаунт через форму входа");
        headerSteps.goToLoginPage();
        signInSteps.checkThatMainElementsOfThePageAreVisible();
        signInSteps.signIn(user);

        LOG.info("6. Проверка, что вход в аккаунт был произведен успешно");
        personalRoomSteps.checkThatMainElementsOfThePageAreVisible();

    }


}
