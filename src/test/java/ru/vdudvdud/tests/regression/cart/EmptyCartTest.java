package ru.vdudvdud.tests.regression.cart;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.CartSteps;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.builders.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class EmptyCartTest extends BaseTest {

    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private RegistrationScenarios registrationScenariosScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
    }

    @Test
    public void runTest() {
        LOG.info("Произвести регистрацию пользователя");
        registrationScenariosScenarios.registration(user);

        LOG.info("Открытие корзины и проверка того, что корзина пустая");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatCartIsEmpty();
    }
}
