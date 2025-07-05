package org.skypro.skyshopv1_1.model.product.specialProducts;

import org.skypro.skyshopv1_1.model.product.Product;

public class FixPriceProduct extends Product{

    private static final int FIX_PRODUCT_PRICE = 50;

    public FixPriceProduct(String productName) {
        super(productName);
    }

    @Override
    public double getPrice() {
        return FIX_PRODUCT_PRICE;
    }

    @Override
    public String toString() {
        return String.format("<%s>: Фиксированная цена <%s> рублей", getName(), getPrice());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


}
