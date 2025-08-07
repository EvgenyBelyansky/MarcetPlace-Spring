package org.skypro.skyshopv1_1;

import org.skypro.skyshopv1_1.model.article.Article;
import org.skypro.skyshopv1_1.model.product.SimpleProduct;
import org.skypro.skyshopv1_1.model.product.specialProducts.DiscountedProduct;
import org.skypro.skyshopv1_1.model.product.specialProducts.FixPriceProduct;
import org.skypro.skyshopv1_1.model.search.Searchable;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestHelper {

    private final static Random random = new Random();

    public static Searchable createRandomSearchable(String name) {
        int randomNumber = random.nextInt(0,4);
        return switch (randomNumber) {
            case 0 -> new Article(name, "Description");
            case 1 -> new DiscountedProduct(name, random.nextInt(0, 100), random.nextInt(0, 100000));
            case 2 -> new FixPriceProduct(name);
            case 3 -> new SimpleProduct(name, random.nextInt(0, 100000));
            default -> throw new RuntimeException();
        };
    }

    public static List<Searchable> createRandomSearchables(String name, int amount) {
        return Stream.generate(() -> createRandomSearchable(name))
                .limit(amount)
                .toList();
    }

    public static List<Searchable> createRandomSearchables(String name) {
        return createRandomSearchables(name, random.nextInt(1,40));
    }
}
