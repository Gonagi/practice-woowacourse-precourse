package store.domain;

import store.domain.product.Product;

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
        if (product.checkPromotionDate()) {
            buyPromotionAndBasicProducts(product);
            return;
        }
        buyBasicProducts(product);
    }

    private void buyBasicProducts(Product product) {
        Product basicProduct = findBasicProduct(product);
        receipt.update(basicProduct);
    }

    private void buyPromotionAndBasicProducts(Product product) {
        Product promotionProduct = findPromotionProduct(product);
        Product basicProduct = findBasicProduct(product);
        receipt.update(promotionProduct, basicProduct);
    }

    private Product findPromotionProduct(final Product findProduct) {
        return storage.findPromotionProduct(findProduct);
    }

    private Product findBasicProduct(final Product findProduct) {
        return storage.findBasicProduct(findProduct);
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Storage getStorage() {
        return storage;
    }
}
