package ru.vdudvdud.tests.regression.registration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.RegistrationSteps;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.enums.UserAliases;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UnsuccessfulRegistrationWrongParametersTest extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();

    private User user;

    @BeforeMethod
    @Parameters("userName")
    public void readParams(UserAliases userName) {
        user = UsersCreator.createUser(userName);
        user.setEmail(String.format(user.getEmail(), TestDataProvider.generateCurrentTimeStamp()));
    }

    @Test
    public void runTest() {
        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("2. Заполнение формы регистрации");
        registrationSteps.checkThatMainElementsOfThePageAreVisible();
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("3. Проверка не успешности регистрации");
        // TODO: для большей полноты теста не хватает проверки всплывающих подсказок. Их нет, так как сайт их не предсматирвает
        registrationSteps.waitUntilSignUpBtnVisible();
        mainPageSteps.waitUntilMainPageNotPresent();
        headerSteps.checkLogoutInvisible();
    }
}