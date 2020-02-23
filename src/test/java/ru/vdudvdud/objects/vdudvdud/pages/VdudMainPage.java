package ru.vdudvdud.objects.vdudvdud.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.vdudvdud.adaptors.selenide.base.BasePage;
import ru.vdudvdud.adaptors.selenide.conditions.CustomCollectionCondition;
import ru.vdudvdud.testdata.constants.Delimiters;
import ru.vdudvdud.testdata.enums.RegexPatterns;
import ru.vdudvdud.testdata.models.essences.Product;
import ru.vdudvdud.testdata.utils.RegexMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс описывающий основную страницу при переходе по базовому урлу до сайта. Страница отображающая товары и табы для переключения по категориям.
 */
public class VdudMainPage extends BasePage { // TODO: Разделить логику на форму с карточками и с карточкой. Вынести из класса логику
    private static final SelenideElement MAIN_ELEMENT = $x("//div[contains(@class, 'nc-items') and .//div[@class='nc-item']]");

    private static final String PRODUCTS_LIST_LOC = "//div[contains(@class, 'home-items__tab-content')]";
    private static final String PRODUCTS_FROM_THE_CATALOG_LOC = PRODUCTS_LIST_LOC + "//div[@class='nc-item']";

    private static final String PRODUCT_STATUS_LOC = ".//span//div[contains(@class, 'badge')]//span";
    private static final String PRODUCT_IMAGE_LOC = ".//div[@class='item__actions']//following-sibling::a[contains(@class, 'item__image')]//img[@class='item__img']";
    private static final String PRODUCT_NAME_LOC = ".//span[@itemprop='name']";
    private static final String PRODUCT_PRICE_AND_CURRENCY_LOC = ".//div[@class='prc']";
    private static final String PRODUCT_EXISTENCE_LOC = ".//div[@class='item__main']//span[@class='stock-text']";
    private static final String PRODUCT_TO_THE_PRODUCT_PAGE_LOC = ".//div[@class='item__main']//button[@type='submit']";
    private static final String PRODUCT_TO_THE_CART_BOTTOM_BTN_LOC = ".//div[@class='item__bottom']//button[@type='submit']";

    private static final String PRODUCT_BY_NAME_PATTERN = PRODUCTS_LIST_LOC + "//div[@class='nc-item' and .//span[@itemprop='name' and contains(text(), '%s')]]";

    @Override
    protected SelenideElement getMainElement() {
        return MAIN_ELEMENT;
    }

    /**
     * Получение случайного продукта со страницы;
     *
     * @return Случайный продукт со страницы с товарами.
     */
    public Product getRandomProduct() {
        ElementsCollection products = getProductsFromTheCatalog();
        SelenideElement productElement = products.get(new Random().nextInt(products.size())).scrollIntoView(true);
        return createProductFromProductCard(productElement);
    }

    /**
     * Получение случайного продукта со страницы за исключением продуктов с указанными именами;
     *
     * @param names - имена исключающие продукты со страницы.
     * @return Случайный продукт со страницы с товарами.
     */
    public Product getRandomProductInsteadSpecifics(String... names) {
        List<SelenideElement> products = getProductsFromTheCatalogNotContainsSpecificNames(names);
        SelenideElement productElement = products.get(new Random().nextInt(products.size())).scrollIntoView(true);
        return createProductFromProductCard(productElement);
    }

    /**
     * Создание продукта и заполнение его данными со страницы согласно переданной карточке продукта.
     *
     * @param productElement Карточка продукта со страницы
     * @return Сформированный десериализованный объект.
     */
    private Product createProductFromProductCard(SelenideElement productElement) {
        Product product = new Product();
        String productFullPrice = productElement.$x(PRODUCT_PRICE_AND_CURRENCY_LOC).shouldBe(Condition.visible).getText();

        SelenideElement productStatus = productElement.$x(PRODUCT_STATUS_LOC);
        if (productStatus.is(Condition.visible)) {
            product.setProductStatus(productElement.$x(PRODUCT_STATUS_LOC).getText());
        }
        product.setImgLink(productElement.$x(PRODUCT_IMAGE_LOC).getAttribute("src"));
        product.setName(productElement.$x(PRODUCT_NAME_LOC).getText());
        product.setCost(Integer.parseInt(RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(productFullPrice,
                RegexPatterns.DIGITS.toString())));
        product.setCurrency(RegexMatcher.regexGetFirstMatchGroupFromTextWithoutSpaces(productFullPrice,
                RegexPatterns.NON_DIGITS.toString().trim()));
        product.setExistence(productElement.$x(PRODUCT_EXISTENCE_LOC).getText());


        return product;
    }

    /**
     * Добавление товара в корзину по имени продукта.
     *
     * @param productName Наименование продукта.
     */
    public void addProductToTheCartByName(String productName) {
        $$x(String.format(PRODUCT_BY_NAME_PATTERN, productName)).filter(Condition.visible).get(0).$x(PRODUCT_TO_THE_CART_BOTTOM_BTN_LOC).click();
    }

    /**
     * Переход на страницу товара.
     *
     * @param productName Наименование продукта.
     */
    public void goToTheProductPage(String productName) {
        $$x(String.format(PRODUCT_BY_NAME_PATTERN, productName)).filter(Condition.visible).get(0).$x(PRODUCT_TO_THE_PRODUCT_PAGE_LOC).click();
    }

    /**
     * Получение карточек продуктов с главной страницы. Ищем карточки до тех пор, пока они не перестанут появляться на странице.
     *
     * @return Карточки продуктов со страницы.
     */
    private ElementsCollection getProductsFromTheCatalog() {
        return $$x(PRODUCTS_FROM_THE_CATALOG_LOC).shouldHave(CustomCollectionCondition.stopAppearNew()).filter(Condition.exist);
    }

    /**
     * Получение карточек продуктов с главной страницы. Ищем карточки до тех пор, пока они не перестанут появляться на странице.
     * Исключаются из поиска карточки, которые в имени содержат указанные имена.
     *
     * @return Карточки продуктов со страницы.
     */
    private List<SelenideElement> getProductsFromTheCatalogNotContainsSpecificNames(String... names) {
        return $$x(PRODUCTS_FROM_THE_CATALOG_LOC).shouldHave(CustomCollectionCondition.stopAppearNew())
                .filter(Condition.exist)
                .stream()
                .filter(el ->  Arrays.asList(names).contains(el.$x(PRODUCT_NAME_LOC).getText()))
                .collect(Collectors.toList());
    }
}
