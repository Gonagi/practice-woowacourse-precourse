package store.domain;

import static store.constants.Messages.NON_EXIST_PRODUCT;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import store.domain.product.Product;
import store.domain.product.Products;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;
import store.util.FileUtils;

public class Storage {
    private final Products products;
    private final Promotions promotions;

    private Storage(final Products products, final Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public static Storage from(final String productFilePath, final String promotionFilePath)
            throws FileNotFoundException {
        Promotions promotions = getPromotions(promotionFilePath);
        Products products = getProducts(productFilePath, promotions);

        return new Storage(products, promotions);
    }

    public Product findPromotionProduct(final Product findProduct) {
        return getProducts().stream()
                .filter(product -> Objects.equals(product.getName(), findProduct.getName()))
                .findFirst()
                .orElse(null);
    }

    public Product findBasicProduct(final Product findProduct) {
        return getProducts().stream()
                .filter(product -> Objects.equals(product.getName(), findProduct.getName()))
                .filter(product -> product.getPromotion() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NON_EXIST_PRODUCT.getErrorMessage()));
    }

    public List<Product> getProducts() {
        return products.getProducts();
    }

    public List<Promotion> getPromotions() {
        return promotions.getPromotions();
    }

    private static Products getProducts(String productFilePath, Promotions promotions) throws FileNotFoundException {
        List<String> productLines = FileUtils.readLinesFromFile(productFilePath);
        List<Product> productList = productLines.stream()
                .map(productLine -> FileUtils.lineToProduct(productLine, promotions))
                .toList();
        return Products.from(productList);
    }

    private static Promotions getPromotions(String promotionFilePath) throws FileNotFoundException {
        List<String> promotionLines = FileUtils.readLinesFromFile(promotionFilePath);
        List<Promotion> promotionList = promotionLines.stream()
                .map(FileUtils::lineToPromotion)
                .toList();
        return Promotions.from(promotionList);
    }
}
