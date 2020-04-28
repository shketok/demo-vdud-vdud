package ru.vdudvdud.tests.regression.cart.empty;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.localization.NavigationLocalizationHolder;
import ru.vdudvdud.steps.vdudvdud.CartSteps;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;
import ru.vdudvdud.testdata.objects.Cart;

public class EmptyCartAddSameProductWithDifferentCharacteristicsUserAuthorized extends BaseTest {
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    @Parameters("tabName")
    public void readParams(NavigationLocalizationHolder tabName) {
        user = UsersCreator.createRandomUser();

        LOG.info("1. Произвести регистрацию пользователя");
        registrationScenarios.registration(user);

        LOG.info("2. Переход на переданную вкладку");
        headerSteps.goToTheProductsTab(tabName.i18n());
    }

    @Test
    public void runTest() {
        LOG.info("1. Добавление 2 одинаковых товара в корзину с разными характеристиками");
        mainPageSteps.clickRandomProductAddToTheCartAndThenAddItAgainWithDifferentSize();

        LOG.info("2. Открытие корзины и проверка корректности отображения основных блоков корзины");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("3. Проверка корректного отображения элементов товара в блоке добавленного товара");
        Cart.getInstance().getProducts().values().forEach(cartSteps::checkThatProductWasAddedToTheCart);

        LOG.info("4. Проверка, что товар добавлен на страницу и в табе товара корректно изменились параметры товара");
        cartSteps.checkThatCartProductTabContainsCorrectData();
    }

}
