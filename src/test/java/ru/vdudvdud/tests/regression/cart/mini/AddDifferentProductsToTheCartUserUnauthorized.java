package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.testdata.models.essences.Product;

//todo abstract class
public class AddDifferentProductsToTheCartUserUnauthorized extends BaseTest {

    private HeaderSteps headerSteps = new HeaderSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();


    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-45")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Добавить случайный товар в корзину");
        Product firstRandomProduct = mainPageSteps.clickRandomProductAddToTheCartBtn();

        LOG.info("Подтверждение добавления товара в корзину");
        mainPageSteps.updateProduct(firstRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(firstRandomProduct);

        LOG.info("Добавить случайный товар в корзину, кроме заранее добавленного");
        Product secondRandomProduct = mainPageSteps.clickRandomProductAddToTheCartExceptSpecifics(firstRandomProduct);
        mainPageSteps.updateProduct(secondRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(secondRandomProduct);

        LOG.info("Проверка, что в мини корзине появилось указанное количество товара");
        headerSteps.checkMiniCart();
    }

}
