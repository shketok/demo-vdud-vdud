package ru.vdudvdud.testdata.models.essences;

import lombok.Data;

/**
 * Класс для хранения информации о продукте из магазина
 */
@Data
public class Product {

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
}
