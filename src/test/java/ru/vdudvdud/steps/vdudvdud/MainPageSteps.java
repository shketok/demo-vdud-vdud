package ru.vdudvdud.steps.vdudvdud;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.objects.vdudvdud.modals.AddProductToTheCartPopup;
import ru.vdudvdud.objects.vdudvdud.modals.ProductAddedToTheCartPopup;
import ru.vdudvdud.objects.vdudvdud.pages.VdudMainPage;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.models.essences.Product;

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
        vdudMainPage.addProductToTheCartByName(product.getName());
        return product;
    }

    @Step("Обновление объекта продукта согласно полученной информации из всплывающего окна подтверждения добавления в корзину")
    public void updateProductFromTheAddToTheCartPopup(Product product) {
        addProductToTheCartPopup.shouldBe(Condition.visible);
        if (addProductToTheCartPopup.isProductSelectedSizeInState(Condition.visible)) {
            product.setSize(addProductToTheCartPopup.getProductSelectedSizeText());
        }
        if (addProductToTheCartPopup.isCountOfTheGoodInState(Condition.visible)) {
            product.setCount(Integer.parseInt(addProductToTheCartPopup.getCountOfTheGoodText()));
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


}
