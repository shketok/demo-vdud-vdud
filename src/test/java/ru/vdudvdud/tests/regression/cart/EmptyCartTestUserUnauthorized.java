package ru.vdudvdud.tests.regression.cart;

import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.CartSteps;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;


public class EmptyCartTestUserUnauthorized extends BaseTest {
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private CartSteps cartSteps = new CartSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    @Test
    public void runTest() {
        LOG.info("1. Открытие главной страницы и формы регистрации");
        mainPageSteps.openMainPage();

        LOG.info("2. Открытие корзины и проверка того, что корзина пустая");
        headerSteps.goToTheCartPage();
        cartSteps.checkThatCartIsEmpty();
    }
}
