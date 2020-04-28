package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;

public class RemoveOneProductFromMiniCartUserUnauthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-50")
    public void runTest() {
        LOG.info("Открыть главную страницу");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Выбрать первый случайный продукт");
        mainPageSteps.clickAndAddRandomProductAddToTheCartWithClosePopup();

        LOG.info("Выбрать второй случайный продукт");
        mainPageSteps.clickAndAddRandomProductAddToTheCartWithClosePopup();

        LOG.info("Удаление продукта из мини-корзины");
        headerSteps.hoverOverMiniCart();
        headerSteps.checkMiniCartVisible();
        mainPageSteps.getMiniCartProducts().stream().findAny()
            .ifPresent(product -> mainPageSteps.removeProductFromMiniCart(product));

        LOG.info("Проверка что в мини-корзине корректные товары");
        mainPageSteps.checkThatMiniCartDataIsCorrect();
    }
}