package store.domain.promotion;

import static store.constants.Messages.INVALID_PROMOTION;

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
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PROMOTION.getErrorMessage()));
    }

    public List<Promotion> getPromotions() {
        return Collections.unmodifiableList(promotions);
    }
}
