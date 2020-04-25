package ru.vdudvdud.tests.regression.cart.mini;

import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.testdata.models.essences.Product;

public class AddTwoDifferentProductToMinicart extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    @Override
    @Test
    public void runTest() {
        mainPageSteps.openMainPage();

        Product firstRandomProduct = mainPageSteps.clickRandomProductAddToTheCartBtn();
        mainPageSteps.updateProduct(firstRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(firstRandomProduct);

        Product secondRandomProduct = mainPageSteps.clickRandomProductAddToTheCartBtn();
        mainPageSteps.updateProduct(secondRandomProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(secondRandomProduct);

        headerSteps.hoverOverMinicart();
        headerSteps.checkMinicartVisible();
        mainPageSteps.checkThatMinicartDataIsCorrect();
    }


}
