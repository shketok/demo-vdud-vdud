package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class RemoveOneProductFromMiniCartUserAuthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    public void readParams() {
        LOG.info("Произвести регистрацию пользователем");
        user = UsersCreator.createRandomUser();
        registrationScenarios.registration(user);
        BrowserUtils.restartBrowser();
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-50")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем и открыть главную страницу");
        authorizationScenarios.authorize(user);
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