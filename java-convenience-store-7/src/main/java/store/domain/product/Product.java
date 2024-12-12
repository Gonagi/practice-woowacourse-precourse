package store.domain.product;

import java.time.LocalDate;
import java.util.Objects;
import store.domain.promotion.Promotion;
import store.util.Translator;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final Promotion promotion;

    private Product(final Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.promotion = builder.promotion;
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

    public static class Builder {
        String name;
        int price;
        int quantity;
        Promotion promotion;

        public Builder(final String name, final int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public Builder price(final int price) {
            this.price = price;
            return this;
        }

        public Builder promotion(final Promotion promotion) {
            this.promotion = promotion;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
