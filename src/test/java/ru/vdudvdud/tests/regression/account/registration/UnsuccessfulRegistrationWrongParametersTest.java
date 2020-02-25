package ru.vdudvdud.tests.regression.account.registration;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.RegistrationSteps;
import ru.vdudvdud.testdata.builders.UsersCreator;
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
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-4")
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-5")
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-6")
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-7")
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-8")
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-9")
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