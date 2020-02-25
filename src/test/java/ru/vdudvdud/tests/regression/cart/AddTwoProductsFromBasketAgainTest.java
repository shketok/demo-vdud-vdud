package ru.vdudvdud.tests.regression.cart;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.steps.CartSteps;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.scenarios.ProductScenarios;
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
        secondProduct = productScenarios.addProductToTheCart(() -> mainPageSteps.clickRandomProductAddToTheCartInsteadSpecifics(firstProduct));
        BrowserUtils.restartBrowser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-24")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Добавить товары в корзину, которые уже там присутствуют");
        Stream.of(firstProduct, secondProduct).forEach(product ->
                productScenarios.addProductToTheCart(() -> mainPageSteps.clickSpecificProductAddToTheCartBtn(product))
        );

        LOG.info("Подтверждение добавления товара в корзину");
        headerSteps.goToTheCartPage();

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(product -> cartSteps.checkThatProductWasAddedToTheCart(product));

        LOG.info("Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
    }
}
