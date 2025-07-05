package org.skypro.skyshopv1_1.model.basket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.product.Product;

@RequiredArgsConstructor
@Getter
public class BasketItem {

    private final Product product;

    private final Integer countProduct;
}
