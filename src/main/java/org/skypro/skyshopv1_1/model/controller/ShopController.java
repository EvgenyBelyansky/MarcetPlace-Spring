package org.skypro.skyshopv1_1.model.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.skyshopv1_1.model.article.Article;
import org.skypro.skyshopv1_1.model.basket.UserBasket;
import org.skypro.skyshopv1_1.model.product.Product;
import org.skypro.skyshopv1_1.model.search.SearchResult;
import org.skypro.skyshopv1_1.model.service.BasketService;
import org.skypro.skyshopv1_1.model.service.SearchService;
import org.skypro.skyshopv1_1.model.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

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

    @GetMapping("/basket/{id}")
    public Object addProduct(@PathVariable("id")UUID id) {

        basketService.addProductsInBasket(id);

        return "*Продукт успешно добавлен*";
    }

    public UserBasket getUserBasket() {
       return basketService.getUserBasket();
    }
}
