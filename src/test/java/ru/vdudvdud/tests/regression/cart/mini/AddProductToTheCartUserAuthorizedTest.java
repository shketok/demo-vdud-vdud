package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.HeaderSteps;
import ru.vdudvdud.steps.MainPageSteps;
import ru.vdudvdud.steps.RegistrationSteps;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;

public class AddProductToTheCartUserAuthorizedTest extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationSteps registrationSteps = new RegistrationSteps();

    private User user;

    @BeforeMethod
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-44")
    public void readParams() {
        user = UsersCreator.createRandomUser();

        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();
        headerSteps.openSignUp();

        LOG.info("2. Заполнение формы регистрации");
        registrationSteps.fillRegistrationData(user);
        registrationSteps.sendRegistrationData();

        LOG.info("3. Проверка успешности регистрации");
        mainPageSteps.checkThatMainElementsOfThePageAreVisible();
        mainPageSteps.checkThatMainPageIsOpen();
        headerSteps.checkLogoutVisible();
    }

    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-44")
    public void runTest() {
        LOG.info("1. Нажатие кнопки В корзину на случайном товаре с главной страницы");
        Product product = mainPageSteps.clickRandomProductAddToTheCartBtn();

        LOG.info("2. Подтверждение добавления товара в корзину");
        mainPageSteps.updateProduct(product);
        mainPageSteps.confirmAddProductToTheCart();

        LOG.info("3. Закрытие формы подтверждения того, что товар был добавлен в корзину");
        mainPageSteps.closeProductAddedPopup(product);

        LOG.info("4. Проверка, что в мини корзине появилось указанное количество товара");
        headerSteps.checkMiniCart();
    }
}
