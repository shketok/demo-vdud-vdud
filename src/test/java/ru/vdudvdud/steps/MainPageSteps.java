package ru.vdudvdud.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.vdudvdud.page.objects.vdudvdud.forms.main.ProductCardForm;
import ru.vdudvdud.page.objects.vdudvdud.modals.AddProductToTheCartPopup;
import ru.vdudvdud.page.objects.vdudvdud.modals.ProductAddedToTheCartPopup;
import ru.vdudvdud.page.objects.vdudvdud.pages.VdudMainPage;
import ru.vdudvdud.testdata.constants.CartConstants;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.enums.urls.BaseUrls;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.objects.Cart;
import ru.vdudvdud.testdata.utils.RegexMatcher;

import java.util.Arrays;

public class MainPageSteps extends BaseSteps {
    private VdudMainPage vdudMainPage = new VdudMainPage();
    private AddProductToTheCartPopup addProductToTheCartPopup = new AddProductToTheCartPopup();
    private ProductAddedToTheCartPopup productAddedToTheCartPopup = new ProductAddedToTheCartPopup();

    @Override
    @Step("Проверка видимости основных элементов на странице")
    public void checkThatMainElementsOfThePageAreVisible() {
        vdudMainPage.shouldBe(Condition.visible);
        vdudMainPage.getProductCardsForm().shouldBe(Condition.visible);
        vdudMainPage.getProductCardsForm().getRandomProductForm().shouldBe(Condition.visible);
    }

    /**
     * Создание продукта и заполнение его данными со страницы согласно переданной карточке продукта.
     *
     * @param productElement Карточка продукта со страницы
     * @return Сформированный десериализованный объект.
     */
    private Product createProductFromProductCard(ProductCardForm productElement) {
        Product product = new Product();
        productElement.shouldBe(Condition.visible).scrollIntoView(true);
        String productFullPrice = productElement.getProductPriceAndCurrencyText();

        if (productElement.isProductStatusInState(Condition.visible)) {
            product.setProductStatus(productElement.getProductStatusText());
        }
        product.setImgLink(productElement.getProductImageSource());
        product.setName(productElement.getProductNameText());
        product.setCost(Integer.parseInt(RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(productFullPrice,
                RegexPatterns.DIGITS.toString())));
        product.setCurrency(RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(productFullPrice,
                RegexPatterns.NON_DIGITS.toString().trim()));
        product.setExistence(productElement.getProductExistenceText());

        return product;
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
        Product product = createProductFromProductCard(vdudMainPage.getProductCardsForm().getRandomProductForm());
        clickSpecificProductAddToTheCartBtn(product);
        return product;
    }

    @Step("Нажатие кнопки В корзину у конкретного выбранного продукта")
    public Product clickSpecificProductAddToTheCartBtn(Product product) {
        vdudMainPage.getProductCardsForm().getProductCardForm(product.getName()).clickProductToTheCartBottomBtn();
        return product;
    }

    @Step("Нажатие кнопки В корзину у случайного продукта кроме указанных")
    public Product clickRandomProductAddToTheCartInsteadSpecifics(Product... products) {
        ProductCardForm productCardForm = vdudMainPage.getProductCardsForm().getRandomProductInsteadSpecifics(
                Arrays.stream(products).map(Product::getName).toArray(String[]::new));

        Product product = createProductFromProductCard(productCardForm);
        clickSpecificProductAddToTheCartBtn(product);
        return product;
    }

    @Step("Получение модели продукта из табы с добавлением товара в корзину")
    public void updateProduct(Product product) {
        if (addProductToTheCartPopup.isElementInState(Condition.visible)) {
            if (addProductToTheCartPopup.isProductSelectedSizeInState(Condition.visible)) {
                product.setModel(addProductToTheCartPopup.getProductSelectedSizeText());
            } else if (addProductToTheCartPopup.isProductSelectedModelInState(Condition.visible)) {
                product.setModel(addProductToTheCartPopup.getProductSelectedModelText());
            }

            if (addProductToTheCartPopup.isCountOfTheGoodInState(Condition.visible)) {
                product.setCount(Integer.parseInt(addProductToTheCartPopup.getCountOfTheGoodText()));
            } else {
                product.setCount(CartConstants.BASE_PRODUCT_COUNT);
            }
        } else {
            product.setCount(CartConstants.BASE_PRODUCT_COUNT);
        }
    }

    @Step("Обновление внутритестовых данных. Обновление корзины согласно параметрам добавленного продукта")
    private void updateLocalCart(Product product) {
        Cart.getInstance().putProduct(product.clone());
    }

    @Step("Подтверждение добавления товара в корзину")
    public void confirmAddProductToTheCart() {
        addProductToTheCartPopup.clickConfirmBtn();
    }

    @Step("Закрытие всплывающего окна уведомляющего о добавлении товара в корзину")
    public void closeProductAddedPopup(Product addedProduct) {
        updateLocalCart(addedProduct);
        productAddedToTheCartPopup.clickClose();
    }

    @Step("Переход в корзину через всплывающее окно добавление товара в корзину")
    public void goToTheCartProductAddedPopup(Product addedProduct) {
        updateLocalCart(addedProduct);
        productAddedToTheCartPopup.clickToCart();
    }


}
