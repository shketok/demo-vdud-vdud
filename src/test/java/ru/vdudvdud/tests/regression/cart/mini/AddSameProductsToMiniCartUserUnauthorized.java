package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.localization.NavigationLocalizationHolder;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.ProductScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;

public class AddSameProductsToMiniCartUserUnauthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();
    private ProductScenarios productScenarios = new ProductScenarios();

    @BeforeMethod
    @Parameters("tabName")
    public void readParams(NavigationLocalizationHolder tabName) {
        LOG.info("Произвести авторизацию пользователем и открыть главную страницу");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("2. Переход на переданную вкладку");
        headerSteps.goToTheProductsTab(tabName.i18n());
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-46")
    public void runTest() {
        LOG.info("Добавить в корзину один и тот же продукт дважды");
        productScenarios.addRandomProductToTheCartWithRepeat();

        LOG.info("Проверка что в мини-корзине корректные товары");
        headerSteps.hoverOverMiniCart();
        headerSteps.checkMiniCartVisible();
        mainPageSteps.checkThatMiniCartDataIsCorrect();
    }


}
