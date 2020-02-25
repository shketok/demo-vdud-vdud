package ru.vdudvdud.testdata.models.essences;

import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;
import ru.vdudvdud.testdata.constants.Delimiters;

import java.io.Serializable;

/**
 * Класс для хранения информации о продукте из магазина
 */
@Data
public class Product implements Serializable {

    /**
     * Статус товара указанный на странице. Обычно это "новый" "распродано" или отсутствие статуса
     */
    private String productStatus;

    /**
     * Ссылка на изображение товара
     */
    private String imgLink;

    /**
     * Полное наименование товара
     */
    private String name;

    /**
     * Цена товара
     */
    private int cost;

    /**
     * Валюта, в которой указана цена товара
     */
    private String currency;

    /**
     * Строка указывающая на наличие товара
     */
    private String existence;

    /**
     * Размер указанного товара при наличии.
     */
    private String model;

    /**
     * Количество указанного товара.
     */
    private int count;

    public Product clone() {
        return SerializationUtils.clone(this);
    }

    /**
     * Получение полного наименования продукта, с его размером. Такое же имя, как и в корзине.
     * @return Полное имя продукта
     */
    public String getFullName() {
        return String.join(Delimiters.SPACE_DELIMITER, name, model);
    }
}
