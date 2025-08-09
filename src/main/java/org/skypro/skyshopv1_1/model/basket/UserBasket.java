package org.skypro.skyshopv1_1.model.basket;

import lombok.Data;

import java.util.List;

@Data
public class UserBasket {

    private final List<BasketItem> basketItemsList;

    private double total;

    public UserBasket(List<BasketItem> basketItemsList) {
        this.basketItemsList = basketItemsList;
        this.total = calculateTotal(basketItemsList);
    }

    private double calculateTotal(List<BasketItem> basketItemsList) {
        return total = basketItemsList.stream()
                .mapToDouble(b -> b.getProduct().getPrice() * b.getCountProduct())
                .sum();
    }
}