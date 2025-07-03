package org.skypro.skyshopv1_1.model.basket;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@SessionScope
@Component
public class ProductBasket {


    private final Map<UUID, Integer> productBasket;

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
