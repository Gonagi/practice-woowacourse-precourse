package store.domain.promotion;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Promotions {
    private final List<Promotion> promotions;

    private Promotions(final List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public static Promotions from(final List<Promotion> promotions) {
        return new Promotions(promotions);
    }

    public Promotion findPromotionByName(final String name) {
        return promotions.stream()
                .filter(promotion -> Objects.equals(promotion.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Promotion> getPromotions() {
        return Collections.unmodifiableList(promotions);
    }
}
