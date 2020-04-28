package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.ProductScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class AddSameProductToMiniCartUserAuthorized extends BaseTest {

    private HeaderSteps headerSteps = new HeaderSteps();
    private ProductScenarios productScenarios = new ProductScenarios();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    public void readParams() {
        LOG.info("Произвести регистрацию пользователем");
        user = UsersCreator.createRandomUser();

        registrationScenarios.registration(user);
    }


    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-48")
    @Override
    public void runTest() {
        LOG.info("1. Произвести авторизацию пользователем");
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("2. Добавление товара в корзину с повтором");
        productScenarios.addProductToTheCartWithRepeat();

        LOG.info("3. Проверка, что в мини корзине появилось указанное количество товара");
        headerSteps.checkMiniCart();
    }
}