package org.skypro.skyshopv1_1.model.product.specialProducts;

import org.skypro.skyshopv1_1.model.product.Product;

import java.util.Objects;

public class DiscountedProduct extends Product {

    private double discountPercentage;
    private double basePrice;

    public DiscountedProduct(String productName, int discountPercentage, int basePrice) {
        super(productName);

        validDiscountPercentage(discountPercentage);
        validBasePrice(basePrice);

        this.discountPercentage = Math.abs(discountPercentage);
        this.basePrice = Math.abs(basePrice);
    }

    private void validBasePrice(double price) {
        if (0 >= price) {
            throw new IllegalArgumentException(
                    "Цена не может быть меньше или равна НУЛЮ!!"
            );
        }
    }

    private void validDiscountPercentage(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(
                    "Некорректное значение скидки!! Процент должен быть числом в диапазоне от 0 до 100 включительно"
            );
        }
    }

    @Override
    public double getPrice() {
        return basePrice * (discountPercentage / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return discountPercentage == that.discountPercentage && basePrice == that.basePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountPercentage, basePrice);
    }

    @Override
    public String toString() {
        return String.format("<%s>: <%s>, (%s%% скидка) ", getName(), getPrice(), discountPercentage);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
