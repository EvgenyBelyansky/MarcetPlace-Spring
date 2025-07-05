package org.skypro.skyshopv1_1.model.exceptions;

public class ArticleIsNullException extends RuntimeException {

    public ArticleIsNullException(String productName) {
        super("Статья про продукт %s не создана. Создайте статью!".formatted(productName));
    }
}
