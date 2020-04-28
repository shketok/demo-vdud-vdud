package ru.vdudvdud.tests.regression.cart.mini;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vdudvdud.adaptors.selenide.base.BaseTest;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.steps.vdudvdud.scenarios.RegistrationScenarios;
import ru.vdudvdud.testdata.creators.UsersCreator;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;

public class AddTwoProductsToMiniCartWithRepeatInSameOrderUserAuthorized extends BaseTest {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();
    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    private User user;

    @BeforeMethod
    public void readParams() {
        LOG.info("Произвести регистрацию пользователем");
        user = UsersCreator.createRandomUser();
        registrationScenarios.registration(user);
    }

    @Override
    @Test
    @Link("https://outsourceofthebrain.myjetbrains.com/youtrack/issue/VDUDUD-49")
    public void runTest() {
        LOG.info("Открыть главную страницу");
        mainPageSteps.openMainPage();
        headerSteps.checkThatMainElementsOfThePageAreVisible();

        LOG.info("Выбрать первый случайный продукт");
        Product firstProduct = mainPageSteps.clickAndAddRandomProductAddToTheCartWithClosePopup();

        LOG.info("Выбрать второй случайный продукт и подтвердить выбор");
        Product secondProduct = mainPageSteps.clickRandomProductAddToTheCartExceptSpecifics(firstProduct);
        mainPageSteps.confirmAddProductToTheCart();
        mainPageSteps.closeProductAddedPopup(secondProduct);

        LOG.info("Добавить повторно первый продукт");
        mainPageSteps.clickSpecificProductAddToTheCartWithConfirmationAndContinue(firstProduct);

        LOG.info("Добавить повторно второй продукт");
        mainPageSteps.clickSpecificProductAddToTheCartWithConfirmationAndContinue(secondProduct);

        LOG.info("Проверка что в мини-корзине корректные товары");
        headerSteps.hoverOverMiniCart();
        headerSteps.checkMiniCartVisible();
        mainPageSteps.checkThatMiniCartDataIsCorrect();
    }


}
