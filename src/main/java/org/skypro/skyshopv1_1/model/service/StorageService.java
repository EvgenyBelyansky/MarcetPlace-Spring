package org.skypro.skyshopv1_1.model.service;


import org.skypro.skyshopv1_1.model.article.Article;
import org.skypro.skyshopv1_1.model.exceptions.NoSuchProductException;
import org.skypro.skyshopv1_1.model.product.Product;
import org.skypro.skyshopv1_1.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class StorageService {

    private final Map<UUID, Product> productMap;

    private final Map<UUID, Article> articleMap;


    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
    }

    public List<Product> getProducts() {
        return productMap.values()
                .stream()
                .toList();
    }

    public List<Article> getArticle() {
        return articleMap.values()
                .stream()
                .toList();
    }

    public List<Searchable> getSearchables() {
        return Stream.concat(getProducts().stream(), getArticle().stream())
                .collect(Collectors.toList());
    }

    public Product getProductById(UUID id) {

        return Optional.ofNullable(productMap.get(id))
                .orElseThrow(() -> new NoSuchProductException(id));
    }
}
