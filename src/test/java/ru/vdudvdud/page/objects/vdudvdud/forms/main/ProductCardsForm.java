package ru.vdudvdud.page.objects.vdudvdud.forms.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.PageObject;
import ru.vdudvdud.adaptors.selenide.conditions.CustomCollectionCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductCardsForm extends PageObject {
    private static final String PRODUCTS_LIST_LOC = ".//div[contains(@class, 'home-items__tab-content') " +
            "and contains(@class, 'active')]";

    private ElementsCollection productCards = mainElement.$$x(".//div[@class='nc-item']");

    public ProductCardsForm(SelenideElement parentElement) {
        super(parentElement.$x(PRODUCTS_LIST_LOC));
    }

    /**
     * Получение формы первого найденного продукта со страницы.
     * @return Форма продукта.
     */
    public ProductCardForm getProductCardFormWithFirstFoundProduct() {
        return new ProductCardForm(productCards.shouldHave(CustomCollectionCondition.stopAppearNew()).first());
    }

    /**
     * Получение формы продукта со страницы по имени
     * @param productName наименование продукта.
     * @return Форма продукта.
     */
    public ProductCardForm getProductCardForm(String productName) {
        return new ProductCardForm(mainElement, productName);
    }

    /**
     * Создание формы продукта на основании переданного продукта в конструктор.
     * @param product Найденный продукт
     * @return Созданная форма продукта.
     */
    private ProductCardForm getProductCardForm(SelenideElement product) {
        return new ProductCardForm(product);
    }

    /**
     * Получение случайной формы продукта со страницы;
     *
     * @return Случайная форма продукта со страницы с товарами.
     */
    public ProductCardForm getRandomProductForm() {
        ElementsCollection products = getProductsFromTheCatalog();
        return new ProductCardForm(getRandomProductFromCollection(products));
    }

    public static List<ProductCardsForm> getProductForms(ElementsCollection elements) {
        return elements.stream().map(el -> new ProductCardsForm(el)).collect(Collectors.toList());
    }

    public static  List<ProductCardForm> getProductForms2(ElementsCollection elements) {
        return elements.stream().map(el -> new ProductCardForm(el)).collect(Collectors.toList());
    }

    /**
     * Получение случайной формы продукта со страницы за исключением продуктов с указанными именами;
     *
     * @param names - имена исключающие продукты со страницы.
     * @return Случайная форма продукта со страницы с товарами.
     */
    public ProductCardForm getRandomProductInsteadSpecifics(String... names) {
        List<SelenideElement> products = getProductsFromTheCatalogNotContainsSpecificNames(names);
        return new ProductCardForm(getRandomProductFromCollection(products));
    }

    /**
     * Получение случайного продукта из списка возможных.
     * @param products Список продуктов.
     * @return Случайный продукт со страницы.
     */
    private SelenideElement getRandomProductFromCollection(List<SelenideElement> products) {
        return products.get(new Random().nextInt(products.size()));
    }

    /**
     * Получение карточек продуктов с главной страницы. Ищем карточки до тех пор, пока они не перестанут появляться на странице.
     *
     * @return Карточки продуктов со страницы.
     */
    private ElementsCollection getProductsFromTheCatalog() {
        return productCards.shouldHave(CustomCollectionCondition.stopAppearNew()).filter(Condition.visible);
    }

    /**
     * Получение карточек продуктов с главной страницы. Ищем карточки до тех пор, пока они не перестанут появляться на странице.
     * Исключаются из поиска карточки, которые в имени содержат указанные имена.
     *
     * @return Карточки продуктов со страницы.
     */
    private List<SelenideElement> getProductsFromTheCatalogNotContainsSpecificNames(String... names) {
        List<String> listOfNames = Arrays.asList(names);
        return productCards.shouldHave(CustomCollectionCondition.stopAppearNew())
                .stream()
                .filter(el -> el.is(Condition.visible) && !isProductContainSpecificNames(el, listOfNames))
                .collect(Collectors.toList());
    }

    private boolean isProductContainSpecificNames(SelenideElement product, List<String> names) {
        return names.contains(getProductCardForm(product).getProductNameText());
    }
}
