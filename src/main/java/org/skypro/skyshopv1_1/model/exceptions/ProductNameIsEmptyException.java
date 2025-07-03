package org.skypro.skyshopv1_1.model.exceptions;

public class ProductNameIsEmptyException extends IllegalArgumentException {

    public ProductNameIsEmptyException() {
        super("Не заполнено имя продукта!! Внесите наименование Продукта!");
    }
}
