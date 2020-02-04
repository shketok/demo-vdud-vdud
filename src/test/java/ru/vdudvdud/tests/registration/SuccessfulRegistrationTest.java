package ru.vdudvdud.tests.registration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.RegistrationSteps;
import ru.vdudvdud.testdata.builders.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class SuccessfulRegistrationTest extends BaseTest {

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.getRandomUser();
    }

    @Test
    public void runTest() {
        MainPageSteps mainPageSteps = new MainPageSteps();
        HeaderSteps headerSteps = new HeaderSteps();
        RegistrationSteps registrationSteps = new RegistrationSteps();

        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("2. Заполнение формы регистрации");
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("3. Проверка успешности регистрации");
        registrationSteps.waitUntilSignUpBtnInvisible();
        mainPageSteps.waitUntilMainPageOpen();
        headerSteps.checkLogoutVisible();
    }
}