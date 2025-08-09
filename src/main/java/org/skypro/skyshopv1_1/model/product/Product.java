package org.skypro.skyshopv1_1.model.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.skypro.skyshopv1_1.model.article.Article;
import org.skypro.skyshopv1_1.model.exceptions.ArticleIsNullException;
import org.skypro.skyshopv1_1.model.exceptions.ProductNameIsEmptyException;
import org.skypro.skyshopv1_1.model.search.Searchable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable, Comparable<Searchable> {

    private final UUID id;
    private final String productName;


    public Product(String productName) {
        validProductName(productName);

        this.id = UUID.randomUUID();
        this.productName = productName;
    }

    private void validProductName(String prodName) {
        if (prodName == null || prodName.isBlank()) {
            throw new ProductNameIsEmptyException();
        }
    }

    private void validArticle(Article art) {
        if (art == null) {
            throw new ArticleIsNullException(productName);
        }
    }

    public String getName() {
        return productName;
    }

    public abstract double getPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public String toString() {
        return String.format("<%s>: <%s>", productName, getPrice());
    }

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return productName;
    }

    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }

    @Override
    public int compareTo(Searchable o) {

        int lengthCompare = Integer.compare(
                o.getName().length(),
                this.getName().length()
        );

        if (lengthCompare == 0) {
            return this.getName().compareTo(o.getName());
        }
        return lengthCompare;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

}
