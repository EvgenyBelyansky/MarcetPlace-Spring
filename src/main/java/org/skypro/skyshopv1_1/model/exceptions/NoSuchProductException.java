package org.skypro.skyshopv1_1.model.exceptions;

public class NoSuchProductException extends IllegalArgumentException {
    public NoSuchProductException() {
        super("Нет такого продукта!");
    }
}
