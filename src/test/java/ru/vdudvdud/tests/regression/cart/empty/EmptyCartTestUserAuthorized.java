package ru.vdudvdud.tests.regression.cart.empty;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.CartSteps;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class EmptyCartTestUserAuthorized extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
        LOG.info("1. Произвести регистрацию пользователя");
        registrationScenarios.registration(user);
    }

    @Test
    public void runTest() {
        LOG.info("1. Открытие корзины и проверка того, что корзина пустая");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatCartIsEmpty();
    }
}
