package ru.vdudvdud.tests.regression.cart.add;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.steps.vdudvdud.CartSteps;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.ProductScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.objects.Cart;

import java.util.stream.Stream;

public class AddTwoProductsFromBasketAgainTest extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();

    private ProductScenarios productScenarios = new ProductScenarios();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();

    private User user;
    private Product firstProduct;
    private Product secondProduct;

    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
        firstProduct = productScenarios.addProductToCartAfterRegistration(user);
        secondProduct = productScenarios.addProductToTheCart(() -> mainPageSteps.clickRandomProductAddToTheCartExceptSpecifics(firstProduct));
        BrowserUtils.restartBrowser();
    }


    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-24")
    @Override
    public void runTest() {
        LOG.info("1. Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("2. Добавить товары в корзину, которые уже там присутствуют");
        Stream.of(firstProduct, secondProduct).forEach(product ->
                productScenarios.addProductToTheCart(() -> mainPageSteps.clickSpecificProductAddToTheCartBtn(product))
        );

        LOG.info("3. Подтверждение добавления товара в корзину");
        headerSteps.goToTheCartPage();

        LOG.info("4. Открытие корзины и проверка корректности отображения основных блоков корзины");
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("5. Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(product -> cartSteps.checkThatProductWasAddedToTheCart(product));

        LOG.info("6. Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
    }
}
