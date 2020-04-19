package ru.vdudvdud.steps.vdudvdud.scenarios;

import io.qameta.allure.Step;
import ru.vdudvdud.adaptors.selenide.utils.Logger;
import ru.vdudvdud.steps.vdudvdud.HeaderSteps;
import ru.vdudvdud.steps.vdudvdud.MainPageSteps;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.models.essences.User;

import java.util.function.Supplier;


/**
 * Сценарии по работе с товарами на сайте
 */
public class ProductScenarios {
    protected static final Logger LOG = Logger.getInstance();

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private HeaderSteps headerSteps = new HeaderSteps();

    private RegistrationScenarios registrationScenarios = new RegistrationScenarios();

    @Step("Сценарий добавления товара в корзину авторизованным пользователем")
    public Product addProductToCartAfterRegistration(User user) {
        LOG.info("Выполнение регистрации на сайте пользователем");
        registrationScenarios.registration(user);

        return addProductToTheCart(() -> mainPageSteps.clickRandomProductAddToTheCartBtn());
    }

    @Step("Сценарий добавления товара с выбором количества в корзину авторизованным пользователем")
    public Product addQuantityOfSameProductToCartAfterRegistration(User user, int quantity) {
        LOG.info("Выполнение регистрации на сайте пользователем");
        registrationScenarios.registration(user);

        return addProductToTheCart(() -> mainPageSteps.clickRandomProductAddToTheCartWithQuantitySelection(quantity));
    }

    @Step("Добавление товара в корзину")
    public Product addProductToTheCart(Supplier<Product> productAddFunction) {
        LOG.info("Нажатие кнопки В корзину на случайном товаре с главной страницы");
        Product product = productAddFunction.get();

        LOG.info("Подтверждение добавления товара в корзину");
        mainPageSteps.confirmAddProductToTheCart();

        LOG.info("Закрытие формы подтверждения того, что товар был добавлен в корзину");
        mainPageSteps.closeProductAddedPopup(product);

        LOG.info("Проверка, что в мини корзине появилось указанное количество товара");
        headerSteps.checkMiniCart();

        return product;
    }


}
