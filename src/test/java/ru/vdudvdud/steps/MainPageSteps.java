package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.objects.vdudvdud.modals.AddProductToTheCartPopup;
import ru.vdudvdud.objects.vdudvdud.modals.ProductAddedToTheCartPopup;
import ru.vdudvdud.objects.vdudvdud.pages.VdudMainPage;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.models.essences.Product;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MainPageSteps extends BaseSteps {
    private VdudMainPage vdudMainPage = new VdudMainPage();
    private AddProductToTheCartPopup addProductToTheCartPopup = new AddProductToTheCartPopup();
    private ProductAddedToTheCartPopup productAddedToTheCartPopup = new ProductAddedToTheCartPopup();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        vdudMainPage.shouldBe(Condition.visible);
    }

    @Step("Открытие главной страницы сайта и проверка корректного открытия страницы")
    public void openMainPage() {
        BaseUrls.BASE.navigate();
        checkThatMainPageIsOpen();
    }

    @Step("Проверка корректного открытия главной страницы")
    public void checkThatMainPageIsOpen() {
        Assert.assertTrue(vdudMainPage.isMainElement(Condition.visible), "Проверка корректного открытия главной страницы");

    }

    @Step("Проверка, что главная страница не была открыта")
    public void waitUntilMainPageNotPresent() {
        vdudMainPage.shouldNotBe(Condition.visible);
    }

    @Step("Нажатие кнопки В корзину у случайного продукта")
    public Product clickRandomProductAddToTheCartBtn() {
        Product product = vdudMainPage.getRandomProduct();
        return clickSpecificProductAddToTheCartBtn(product);
    }

    @Step("Нажатие кнопки В корзину у конкретного выбранного продукта")
    public Product clickSpecificProductAddToTheCartBtn(Product product) {
        vdudMainPage.addProductToTheCartByName(product.getName());
        return product;
    }

    @Step("Нажатие кнопки В корзину у случайного продукта кроме указанных")
    public Product clickRandomProductAddToTheCartInsteadSpecifics(Product... products) {
        Product product = vdudMainPage.getRandomProductInsteadSpecifics(Arrays.stream(products).map(Product::getName).toArray(String[]::new));
        return clickSpecificProductAddToTheCartBtn(product);
    }

    @Step("Обновление объекта продукта согласно полученной информации из всплывающего окна подтверждения добавления в корзину")
    public void updateProductFromTheAddToTheCartPopup(Product product) {
        if (addProductToTheCartPopup.isElementInState(Condition.visible)) {
            if (addProductToTheCartPopup.isProductSelectedSizeInState(Condition.visible)) {
                product.setModel(addProductToTheCartPopup.getProductSelectedSizeText());
            } else if (addProductToTheCartPopup.isProductSelectedModelInState(Condition.visible)) {
                product.setModel(addProductToTheCartPopup.getProductSelectedModelText());
            }

            if (addProductToTheCartPopup.isCountOfTheGoodInState(Condition.visible)) {
                product.setCount(Integer.parseInt(addProductToTheCartPopup.getCountOfTheGoodText()));
            } else {
                product.setCount(product.getCount() + 1);
            }
        } else {
            product.setCount(product.getCount() + 1);
        }

    }

    @Step("Подтверждение добавления товара в корзину")
    public void confirmAddProductToTheCart() {
        addProductToTheCartPopup.clickConfirmBtn();
    }

    @Step("Закрытие всплывающего окна уведомляющего о добавлении товара в корзину")
    public void closeProductAddedPopup() {
        productAddedToTheCartPopup.clickClose();
    }

    @Step("Переход в корзину через всплывающее окно добавление товара в корзину")
    public void goToTheCartProductAddedPopup() {
        productAddedToTheCartPopup.clickToCart();
    }


}
