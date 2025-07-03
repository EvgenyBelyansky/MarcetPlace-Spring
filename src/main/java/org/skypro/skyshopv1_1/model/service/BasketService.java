package org.skypro.skyshopv1_1.model.service;

import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.basket.BasketItem;
import org.skypro.skyshopv1_1.model.basket.ProductBasket;
import org.skypro.skyshopv1_1.model.basket.UserBasket;
import org.skypro.skyshopv1_1.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final StorageService storageService;

    private final ProductBasket productBasket;

    public void addProductsInBasket(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new IllegalArgumentException();
        }

        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketProducts = productBasket.getBasket();

        List<BasketItem> items = basketProducts.entrySet().stream()
                .map(entry -> {
                            Product product = storageService.getProductById(entry.getKey())
                                    .orElseThrow(IllegalStateException::new);
                            return new BasketItem(product, entry.getValue());
                        }
                )
                .collect(Collectors.toList());

        return new UserBasket(items);
    }
}
