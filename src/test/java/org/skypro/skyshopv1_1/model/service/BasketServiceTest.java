package org.skypro.skyshopv1_1.model.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshopv1_1.model.basket.BasketItem;
import org.skypro.skyshopv1_1.model.basket.ProductBasket;
import org.skypro.skyshopv1_1.model.basket.UserBasket;
import org.skypro.skyshopv1_1.model.exceptions.NoSuchProductException;
import org.skypro.skyshopv1_1.model.product.Product;
import org.skypro.skyshopv1_1.model.product.SimpleProduct;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    private static final Random RANDOM = new Random();

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    @DisplayName("Выбрасывается исключение, если в корзину добавляется несуществующий товар")
    void addProductsInBasketTest1() {
        UUID id = UUID.randomUUID();

        Mockito.when(storageService.getProductById(id)).thenThrow(NoSuchProductException.class);

        assertThatExceptionOfType(NoSuchProductException.class).isThrownBy(() -> basketService.addProductsInBasket(id));

        Mockito.verify(productBasket, Mockito.never()).addProduct(id);
        Mockito.verify(storageService, Mockito.only()).getProductById(id);
    }

    @Test
    @DisplayName("Товар добавляется, когда в корзине нет такого товара и он существует")
    void addProductsInBasketTest2() {

        Product simpleProduct = new SimpleProduct("Apple", 100);

        Mockito.when(storageService.getProductById(simpleProduct.getId())).thenReturn(simpleProduct);

        basketService.addProductsInBasket(simpleProduct.getId());

        Mockito.verify(productBasket, Mockito.only()).addProduct(simpleProduct.getId());
        Mockito.verify(storageService, Mockito.only()).getProductById(simpleProduct.getId());
    }


    @Test
    @DisplayName("Метод возвращает пустую корзину если в ней нет товаров")
    void getUserBasketTest1() {

        Mockito.when(productBasket.getBasket()).thenReturn(Map.of());

        final UserBasket actual = basketService.getUserBasket();

        assertThat(actual).isNotNull();
        assertThat(actual.getBasketItemsList()).isEmpty();
        assertThat(actual.getTotal()).isZero();

        Mockito.verify(storageService, Mockito.never()).getProductById(any());
        Mockito.verify(productBasket, Mockito.only()).getBasket();
    }

    @Test
    @DisplayName("Метод возвращает список продуктов, если они есть в корзине")
    void getUserBasketTest2() {

        Product simpleProduct = new SimpleProduct("Apple", 100);
        Product simpleProduct1 = new SimpleProduct("Apple1", 100);
        Product simpleProduct2 = new SimpleProduct("Apple2", 100);

        Map<UUID, Integer> productMap = Map.of(
                simpleProduct.getId(), 2,
                simpleProduct1.getId(), 1,
                simpleProduct2.getId(), 54
        );

        Mockito.when(productBasket.getBasket()).thenReturn(productMap);

        Mockito.when(storageService.getProductById(simpleProduct.getId())).thenReturn(simpleProduct);
        Mockito.when(storageService.getProductById(simpleProduct1.getId())).thenReturn(simpleProduct1);
        Mockito.when(storageService.getProductById(simpleProduct2.getId())).thenReturn(simpleProduct2);

        final UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket).isNotNull();
        assertThat(userBasket.getTotal()).isEqualTo(Stream.of(simpleProduct,simpleProduct1,simpleProduct2)
                .mapToDouble(product -> product.getPrice() * productMap.get(product.getId()))
                .sum());
        assertThat(userBasket.getBasketItemsList()).isNotNull().isNotEmpty();
        assertThat(userBasket.getBasketItemsList())
                .extracting(BasketItem::getProduct)
                .containsExactlyInAnyOrder(simpleProduct,simpleProduct1,simpleProduct2);
        assertThat(userBasket.getBasketItemsList()).allMatch(basketItem -> basketItem.getCountProduct()
                .equals(productMap.get(basketItem.getProduct().getId())));

        Mockito.verify(storageService, Mockito.times(1)).getProductById(simpleProduct.getId());
        Mockito.verify(storageService, Mockito.times(1)).getProductById(simpleProduct1.getId());
        Mockito.verify(storageService, Mockito.times(1)).getProductById(simpleProduct2.getId());
        Mockito.verify(productBasket, Mockito.times(1)).getBasket();
    }
}
