package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.testdata.models.essences.Product;

public class AddTwoDifferentProductToMinicartUserUnauthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-45")
    public void runTest() {
        LOG.info("Открыть главную страницу");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Выбрать первый случайный продукт");
        Product firstRandomProduct = mainPageSteps.clickRandomProductAddToTheCartBtn();
        mainPageSteps.updateProduct(firstRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(firstRandomProduct);

        LOG.info("Выбрать второй случайный продукт");
        Product secondRandomProduct = mainPageSteps.clickRandomProductAddToTheCartBtn();
        mainPageSteps.updateProduct(secondRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(secondRandomProduct);

        LOG.info("Проверка что в мини-корзине корректные товары");
        headerSteps.hoverOverMinicart();
        headerSteps.checkMinicartVisible();
        mainPageSteps.checkThatMinicartDataIsCorrect();
    }


}
