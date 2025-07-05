package org.skypro.skyshopv1_1.model.exceptions;

public class BestResultNotFound extends RuntimeException {
    public BestResultNotFound(String searchText) {
        super("Для поискового запроса |%s| не нашлось подходящей статьи.".formatted(searchText));
    }
}
