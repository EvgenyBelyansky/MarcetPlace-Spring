package org.skypro.skyshopv1_1.model.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.article.Article;
import org.skypro.skyshopv1_1.model.product.Product;
import org.skypro.skyshopv1_1.model.search.SearchResult;
import org.skypro.skyshopv1_1.model.service.SearchService;
import org.skypro.skyshopv1_1.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return storageService.getArticle();
    }

    @GetMapping("/search")
    public List<SearchResult> searchFromPattern(String pattern) {
        return searchService.search(pattern);
    }
}
