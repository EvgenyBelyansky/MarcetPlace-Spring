package org.skypro.skyshopv1_1.model.basket;

import java.util.List;


public class UserBasket {

    private final List<BasketItem> basketItemsList;

    private double total;

    public UserBasket(List<BasketItem> basketItemsList) {
        this.basketItemsList = basketItemsList;
        this.total = calculateTotal();
    }

    public double calculateTotal() {
        return total = basketItemsList.stream()
                .mapToDouble(b -> b.getProduct().getPrice() * b.getCountProduct())
                .sum();
    }
}