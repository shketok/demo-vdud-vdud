package ru.vdudvdud.testdata.objects;

import lombok.Getter;
import ru.vdudvdud.testdata.models.essences.MiniCartProduct;
import ru.vdudvdud.testdata.models.essences.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс воссоздающий дубликат корзины с сайта, служит для проверки параметров корзины.
 * Корзину следует обновлять, когда производятся действия с корзиной.
 */
public class Cart {
    private static ThreadLocal<Cart> instance = new InheritableThreadLocal<>();

    @Getter
    private Map<String, Product> products;

    private Cart() {
        products = new HashMap<>();
    }

    public void clean() {
        products = new HashMap<>();
    }

    public static Cart getInstance() {
        if (instance.get() == null) {
            instance.set(new Cart());
        }
        return instance.get();
    }


    /**
     * Проверка корзины на пустоту.
     * @return True - Корзина пуста. False - в корзине есть товары.
     */
    public boolean isCartEmpty() {
        return products.isEmpty();
    }

    /**
     * Кладет продукт в корзину, если он отсутствует, или же увеличивает количество указанного товара в корзине,
     * если он ранее в ней присутствовал
     * @param product продукт добавленный в корзину
     */
    public void putProduct(Product product) {
        if (isProductInCart(product) && product.getCount() > 0) {
            Product productFromTheCart = products.get(product.getFullName());
            productFromTheCart.setCount(productFromTheCart.getCount() + product.getCount());
        } else if (isProductInCart(product) && product.getCount() <= 0) {
            removeProduct(product);
        } else {
            products.put(product.getFullName(), product);
        }
    }

    /**
     * Проверка присутствия в корзине указанного продукта.
     * @param product Продукт в корзине.
     * @return True - товар присутствует в корзине. False - товар отсутствует в корзине.
     */
    public boolean isProductInCart(Product product) {
        return products.containsKey(product.getFullName());
    }

    /**
     * Удаление продукта из корзины.
     * @param product Продукт в корзине
     */
    public void removeProduct(Product product) {
        if (isCartEmpty() || !isProductInCart(product)) {
            return;
        }
        products.remove(product.getFullName());
    }

    /**
     * Удалить все прдукты из корзины
     */
    public void clearProducts() {
        products.clear();
    }

    /**
     * Устанавливает переданное количество товара в корзине. Количество товара можно изменить явно находясь в корзине.
     * @param product Продукт из табы в корзине
     * @param count Количество продукта, которое было установлено.
     */
    public void updateProductCount(Product product, int count) {
        if (isCartEmpty() || !isProductInCart(product)) {
            return;
        }

        if (count <= 0) {
            removeProduct(product);
        } else {
            products.get(product.getFullName()).setCount(count);
        }
    }

    /**
     * Увеличение количества товара в корзине на единицу.
     * @param product Товар в корзине.
     */
    public void incrementCount(Product product) {
        if (isCartEmpty() || !isProductInCart(product)) {
            return;
        }

        Product specificCollectionProduct = products.get(product.getFullName());
        specificCollectionProduct.setCount(specificCollectionProduct.getCount() + 1);
    }

    /**
     * Уменьшение количества товара в корзине на единицу.
     * @param product Товар в корзине.
     */
    public void decrementCount(Product product) {
        if (isCartEmpty() || !isProductInCart(product)) {
            return;
        }

        Product specificCollectionProduct = products.get(product.getFullName());
        if (specificCollectionProduct.getCount() - 1  <= 0) {
            removeProduct(product);
        } else {
            specificCollectionProduct.setCount(specificCollectionProduct.getCount() - 1);
        }
    }

    /**
     * Получение общей стоимости всех товаров из корзины.
     * Если товаров в корзине нет, тогда полная стоимость будет равна нулю
     * @return Стоимость товаров в корзине.
     */
    public int getTotalCost() {
        if (isCartEmpty()) {
            return 0;
        }

        return products.values().stream().mapToInt(product -> product.getCost() * product.getCount()).sum();
    }

    /**
     * Получение общего количества всех товаров из корзины.
     * Если товаров в корзине нет, тогда количество будет равно нулю
     * @return Количество товаров в корзине.
     */
    public int getTotalProductsCount() {
        if (isCartEmpty()) {
            return 0;
        }

        return products.values().stream().mapToInt(Product::getCount).sum();
    }


    /**
     * Получение текущей валюты, отображаемой в корзине.
     * @return Значение текущей валюты в корзине.
     */
    public String getCartCurrentCurrency() {
        return products.entrySet().iterator().next().getValue().getCurrency();
    }

    /**
     * Содержится ли продукт из мини-корзины в корзине
     *
     */
    public boolean hasMiniCartProduct(MiniCartProduct miniCartProduct) {
        return getProducts().values().stream()
            .anyMatch(
                product ->
                    miniCartProduct.getName().contains(product.getName())
                        && (miniCartProduct.getCost() == (product.getCost() * product.getCount()))
                        && miniCartProduct.getCount() == product.getCount()
                        && miniCartProduct.getCurrency().equals(product.getCurrency()));
    }

    /**
     * Удалить продукт мини-корзины
     *
     */
    public boolean removeMiniCartProduct(MiniCartProduct miniCartProduct) {
        return getProducts().entrySet().removeIf(
            entry -> {
                Product product = entry.getValue();
                return miniCartProduct.getName().contains(product.getName())
                    && (miniCartProduct.getCost() == (product.getCost() * product.getCount()))
                    && miniCartProduct.getCount() == product.getCount()
                    && miniCartProduct.getCurrency().equals(product.getCurrency());
            });
    }
}
