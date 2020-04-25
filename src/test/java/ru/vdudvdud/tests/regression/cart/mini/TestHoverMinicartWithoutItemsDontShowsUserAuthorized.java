package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class TestHoverMinicartWithoutItemsDontShowsUserAuthorized extends BaseTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();


    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-43")
    public void runTest() {
        LOG.info("1. Произвести авторизацию пользователем");
        registrationScenarios.registration(user);

        LOG.info("2. Навести на значок мини корзины");
        headerSteps.hoverOverMinicart();

        LOG.info("3. Проверка, что мини корзина не появилась");
        headerSteps.checkMinicartInvisible();
    }
}