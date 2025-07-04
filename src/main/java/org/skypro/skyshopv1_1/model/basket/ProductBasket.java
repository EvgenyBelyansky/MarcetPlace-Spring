package org.skypro.skyshopv1_1.model.basket;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@RequiredArgsConstructor
@SessionScope
public class ProductBasket {


    private final Map<UUID, Integer> productBasket = new HashMap<>();

    public void addProduct(UUID id) {
        productBasket.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(productBasket);
    }

    public List<UUID> getIdProductsInBasket() {
        return productBasket.keySet().stream()
                .toList();
    }

}
