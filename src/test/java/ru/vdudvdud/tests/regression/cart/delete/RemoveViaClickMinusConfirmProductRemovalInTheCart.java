package ru.vdudvdud.tests.regression.cart.delete;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
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

public class RemoveViaClickMinusConfirmProductRemovalInTheCart extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private ProductScenarios productScenarios = new ProductScenarios();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();

    private User user;
    private Product product;


    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();

        LOG.info("Добавление случайного товара в корзину");
        product = productScenarios.addProductToCartAfterRegistration(user);

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(cartSteps::checkThatProductWasAddedToTheCart);

        LOG.info("Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
        BrowserUtils.restartBrowser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-28")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Нажатие кнопки минус у товара из корзины и подтверждение удаления из корзины");
        cartSteps.clickMinusBtn(product);
        cartSteps.confirmProductRemoval(product);

        LOG.info("Проверка, что после удаления единственного продукта из корзины корзина стала пустой");
        cartSteps.checkThatCartIsEmpty();
    }
}
