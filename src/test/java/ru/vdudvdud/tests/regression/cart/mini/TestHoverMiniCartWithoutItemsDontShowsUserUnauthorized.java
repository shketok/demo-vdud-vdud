package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;

public class TestHoverMiniCartWithoutItemsDontShowsUserUnauthorized extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();


    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-43")
    public void runTest() {
        LOG.info("1. Открытие главной страницы");
        mainPageSteps.openMainPage();

        LOG.info("2. Навести на значок мини корзины");
        headerSteps.hoverOverMiniCart();

        LOG.info("3. Проверка, что мини корзина не появилась");
        headerSteps.checkMiniCartInvisible();
    }
}