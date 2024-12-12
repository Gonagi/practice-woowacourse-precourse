package store.domain;

import java.time.LocalDate;
import java.util.Objects;
import store.util.Translator;

public class Product {
    private final String name;
    private final int price;
    private final Promotion promotion;
    private int quantity;

    private Product(final String name, final int price, final int quantity, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(final String name, final int price, final int quantity, final Promotion promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public boolean checkPromotionDate() {
        LocalDate startDate = promotion.getStartDate();
        LocalDate endDate = promotion.getEndDate();
        LocalDate curLocalDate = Translator.getCurLocalDate();

        if (startDate.isAfter(curLocalDate) || endDate.isBefore(curLocalDate)) {
            return false;
        }
        return true;
    }

    public void addQuantity(final Product product) {
        this.quantity += product.getQuantity();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getPromotionName() {
        return promotion.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
