package store.util;

import static store.constants.Constants.COMMA;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import store.domain.product.Product;
import store.domain.product.Product.Builder;

public final class Translator {
    public static LocalDate getCurLocalDate() {
        LocalDateTime now = DateTimes.now();
        return now.toLocalDate();
    }

    public static List<Product> getProductsByStrings(final String inputProducts) {
        String[] productInfo = inputProducts.split(COMMA);
        return Arrays.stream(productInfo)
                .map(p -> p.substring(1, p.length() - 1))
                .map(p -> {
                    String[] split = p.split("-");
                    return new Builder(split[0], Integer.parseInt(split[1])).build();
                }).toList();
    }
}
