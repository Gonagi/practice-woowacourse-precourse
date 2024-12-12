package store.util;

import static store.constants.Constants.COMMA;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import store.domain.product.Product;
import store.domain.product.Product.Builder;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;

public final class FileUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private FileUtils() {

    }

    public static List<String> readLinesFromFile(final String filePath) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public static Promotion lineToPromotion(final String line) {
        String[] promotionInfo = line.split(COMMA);
        String name = promotionInfo[0];
        int buy = Integer.parseInt(promotionInfo[1]);
        int get = Integer.parseInt(promotionInfo[2]);
        LocalDate startDate = LocalDate.parse(promotionInfo[3], DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(promotionInfo[4], DATE_TIME_FORMATTER);

        return Promotion.of(name, buy, get, startDate, endDate);
    }

    public static Product lineToProduct(final String line, final Promotions promotions) {
        String[] productInfo = line.split(COMMA);
        String name = productInfo[0];
        int price = Integer.parseInt(productInfo[1]);
        int quantity = Integer.parseInt(productInfo[2]);
        String promotionName = productInfo[3];
        Promotion promotion = promotions.findPromotionByName(promotionName);

        return new Builder(name, quantity).price(price).promotion(promotion).build();
    }
}
