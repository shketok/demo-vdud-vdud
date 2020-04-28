package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.adaptors.selenide.utils.BrowserUtils;
import ru.vdudvdud.localization.NavigationLocalizationHolder;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.AuthorizationScenarios;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class AddSameProductsWithDifferentOptionsToMiniCartUserAuthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private AuthorizationScenarios authorizationScenarios = new AuthorizationScenarios();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;
    private String tabName;

    @BeforeMethod
    @Parameters("tabName")
    public void readParams(NavigationLocalizationHolder tabName) {
        this.tabName = tabName.i18n();
        user = UsersCreator.createRandomUser();

        LOG.info("Произвести регистрацию пользователя");
        registrationScenarios.registration(user);

        BrowserUtils.restartBrowser();
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-47")
    public void runTest() {
        LOG.info("Произвести авторизацию пользователем и открыть главную страницу");
        authorizationScenarios.authorize(user);
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Переход на переданную вкладку");
        headerSteps.goToTheProductsTab(tabName);

        LOG.info("Добавить в корзину один и тот же продукт с разными характеристиками");
        mainPageSteps.clickRandomProductAddToTheCartAndThenAddItAgainWithDifferentSize();

        LOG.info("Проверка что в мини-корзине корректные товары");
        headerSteps.hoverOverMiniCart();
        headerSteps.checkMiniCartVisible();
        mainPageSteps.checkThatMiniCartDataIsCorrect();
    }


}
