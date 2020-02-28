package ru.vdudvdud.tests.regression.authorization;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.SignInSteps;
import ru.vdudvdud.steps.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.builders.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.utils.TestDataProvider;

public class UnsuccessfulAuthorizationWithIncorrectPasswordTest extends BaseTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private SignInSteps signInSteps = new SignInSteps();
    private RegistrationScenarios registrationScenarios= new RegistrationScenarios();


    private User user;
    private String incorrectPassword;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
        incorrectPassword = TestDataProvider.generateRandomString();
        registrationScenarios.registrationAndLogout(user);
    }

    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-13")
    @Test
    public void runTest() {
        LOG.info("1. Попытка входа в аккаунт через форму входа с некоррентым паролем и сохранением всех оставшихся данных");
        user.setPassword(incorrectPassword);
        headerSteps.goToLoginPage();
        signInSteps.checkThatMainElementsOfThePageAreVisible();
        signInSteps.signIn(user);

        LOG.info("2. Проверка, что было выведено сообщение о некорректности пароля или почты");
        /* TODO:
         *  здесь есть небольшой плавающий баг сайта и автотеста
         *
         *  плавающий баг автотеста:
         *  при первом клике на кнопку входа в аккаунт сообщение об ошибке может появиться на долю секунды, чего будет достаточно для теста чтобы отработать с успехом
         *  но также может быть недостаточно и тест упадет с ошибкой отсутствия элемента
         *
         *  баг сайта:
         *  сообщения по факту нет до повторного клика
         */
        signInSteps.checkThatEitherPasswordOrEmailIncorrectMessageIsVisible();
    }
}
