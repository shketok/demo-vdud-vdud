package ru.vdudvdud.tests.regression.account.registration;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UnsuccessfulRegistrationPasswordsDoesntMatchTest extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-3")
    public void runTest() {
        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("2. Заполнение формы регистрации");
        registrationSteps.checkThatMainElementsOfThePageAreVisible();
        registrationSteps.fillRegistrationData(user);
        registrationSteps
            .fillRepeatPassword(user.getPassword().concat(TestDataProvider.generateRandomStringWithBaseLength()));
        registrationSteps.sendRegistrationData();

        LOG.info("3. Проверка не успешности регистрации");
        // TODO: для большей полноты теста не хватает проверки всплывающих подсказок. Их нет, так как сайт их не предсматирвает
        registrationSteps.waitUntilSignUpBtnVisible();
        mainPageSteps.waitUntilMainPageNotPresent();
        headerSteps.checkLogoutInvisible();
    }
}