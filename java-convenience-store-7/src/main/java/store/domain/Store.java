package store.domain;

import static store.constants.Messages.NO_QUANTITY;

import store.domain.product.Product;
import store.domain.product.Product.Builder;

public class Store {
    private final Receipt receipt;
    private final Storage storage;

    private Store(final Storage storage) {
        this.receipt = new Receipt();
        this.storage = storage;
    }

    public static Store from(final Storage storage) {
        return new Store(storage);
    }

    public void buyProduct(final Product product) {
        if (product.getPromotion() != null && product.checkPromotionDate()) {
            buyPromotionAndBasicProducts(product);
            return;
        }
        buyBasicProducts(product);
    }

    private void buyBasicProducts(final Product product) {
        Product basicProduct = findBasicProduct(product);

        receipt.addPurchaseProducts(basicProduct);
    }

    private void buyPromotionAndBasicProducts(Product product) {
        Product promotionProduct = findPromotionProduct(product);
        Product basicProduct = findBasicProduct(product);
//        receipt.update(promotionProduct, basicProduct);
    }

    private Product findPromotionProduct(final Product findProduct) {
        return storage.findPromotionProduct(findProduct);
    }

    private Product findBasicProduct(final Product findProduct) {
        Product product = storage.findBasicProduct(findProduct);
        validateStock(findProduct, product);
        product.reduceQuantity(findProduct);
        return new Builder(product.getName(), findProduct.getQuantity())
                .price(product.getPrice()).build();
    }

    private void validateStock(final Product findProduct, final Product product) {
        if (product.getQuantity() < findProduct.getQuantity()) {
            throw new IllegalArgumentException(NO_QUANTITY.getErrorMessage());
        }
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Storage getStorage() {
        return storage;
    }
}
