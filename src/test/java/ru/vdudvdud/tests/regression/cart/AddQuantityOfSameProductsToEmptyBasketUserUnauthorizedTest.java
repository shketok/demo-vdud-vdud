package ru.vdudvdud.tests.regression.cart;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.steps.vdudvdud.CartSteps;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.ProductScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.objects.Cart;

public class AddQuantityOfSameProductsToEmptyBasketUserUnauthorizedTest extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private ProductScenarios productScenarios = new ProductScenarios();

    private Product product;

    @BeforeMethod
    @Parameters("quantity")
    public void readParams(int quantity) {
        product = productScenarios.addQuantityOfSameProductToCart(quantity);
    }


    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-21")
    @Override
    public void runTest() {
        LOG.info("1. Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("2. Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(product -> cartSteps.checkThatProductWasAddedToTheCart(product));

        LOG.info("3. Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
    }
}
