package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.localization.NavigationLocalizationHolder;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.User;

public class AddProductsWithDifferentTypesThenRemoveOneFromMiniCartUserAuthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();
    private String secondTab;

    @BeforeMethod
    @Parameters({"firstTab", "secondTab"})
    public void readParams(NavigationLocalizationHolder firstTab, NavigationLocalizationHolder secondTab) {
        this.secondTab = secondTab.i18n();
        User user = UsersCreator.createRandomUser();

        LOG.info("Произвести регистрацию пользователя");
        registrationScenarios.registration(user);

        LOG.info("Переход на переданную вкладку");
        headerSteps.goToTheProductsTab(firstTab.i18n());
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-51")
    public void runTest() {
        LOG.info("Открыть главную страницу");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Выбрать первый случайный продукт");
        mainPageSteps.clickAndAddRandomProductAddToTheCartWithClosePopup();

        LOG.info("Переход на переданную вкладку #2");
        headerSteps.goToTheProductsTab(secondTab);

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