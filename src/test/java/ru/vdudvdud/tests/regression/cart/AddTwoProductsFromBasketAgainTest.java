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

public class AddTwoProductsFromBasketAgainTest extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();

    private ProductScenarios productScenarios = new ProductScenarios();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();

    private User user;
    private Product product;

    private Integer expectedCount;

    @BeforeMethod
    @Parameters("expectedCount")
    public void readParams(Integer expectedCount) {
        user = UsersCreator.createRandomUser();
        product = productScenarios.addProductToCartAfterRegistration(user);
        BrowserUtils.restartBrowser();

        this.expectedCount = expectedCount;
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-24")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        authorizationScenarios.authorize(user);
        headerSteps.goToTheMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Добавить товар в корзину, который там уже присутствует");
        mainPageSteps.clickSpecificProductAddToTheCartBtn(product);

        LOG.info("Подтверждение добавления товара в корзину");
        mainPageSteps.updateProductFromTheAddToTheCartPopup(product);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.goToTheCartProductAddedPopup();

        LOG.info("Открытие корзины и проверка корректности отображения основных блоков корзины");
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Проверка корректного отображения элементов товара в блоке добавленного товара");
        cartSteps.checkThatProductWasAddedToTheCart(product);

        LOG.info("Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData(product, expectedCount);
    }
}
