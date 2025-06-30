package org.skypro.skyshopv1_1.model.exceptions;

public class ProductNameIsEmptyException extends IllegalArgumentException {

    public ProductNameIsEmptyException() {
      System.out.println("Не заполнено имя продукта!! Внесите наименование Продукта!");
    }
}
