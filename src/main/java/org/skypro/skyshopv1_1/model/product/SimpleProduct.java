package org.skypro.skyshopv1_1.model.product;

import java.util.Objects;

public class SimpleProduct extends Product {

    private int price;

    public SimpleProduct(String productName, int price) {
        super(productName);

        validPrice(price);

        this.price = price;
    }

    private void validPrice(int price) {
        if (0 >= price) {
            throw new IllegalArgumentException("Цена не может быть меньше или равна НУЛЮ!!");
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct that = (SimpleProduct) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public boolean isSpecial() {
        return false;
    }


}
