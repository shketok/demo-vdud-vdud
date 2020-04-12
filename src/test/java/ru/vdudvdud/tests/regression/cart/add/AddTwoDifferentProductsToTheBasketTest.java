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
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.objects.Cart;

public class AddTwoDifferentProductsToTheBasketTest extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();

    private ProductScenarios productScenarios = new ProductScenarios();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;


    @BeforeMethod
    public void readParams() {
        user = UsersCreator.createRandomUser();
        registrationScenarios.registration(user);

        BrowserUtils.restartBrowser();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-20")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Добавить два или более товаров разного типа");
        Product firstProduct = productScenarios.addProductToTheCart(
                () -> mainPageSteps.clickRandomProductAddToTheCartBtn());
        productScenarios.addProductToTheCart(
                () -> mainPageSteps.clickRandomProductAddToTheCartExceptSpecifics(firstProduct));

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(product -> cartSteps.checkThatProductWasAddedToTheCart(product));

        LOG.info("Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
    }


}
