package ru.vdudvdud.tests.regression.cart;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.steps.CartSteps;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.scenarios.ProductScenarios;
import ru.vdudvdud.testdata.builders.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;

public class CheckoutBuyerAuthorizedData extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private ProductScenarios productScenarios = new ProductScenarios();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();

    private User user;
    private Product product;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
        product = productScenarios.addProductToCartAfterRegistration(user);
        BrowserUtils.restartBrowser();
    }

    @Test
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Проверка корректного отображения элементов товара в блоке добавленного товара");
        cartSteps.checkThatProductWasAddedToTheCart(product);
    }
}
