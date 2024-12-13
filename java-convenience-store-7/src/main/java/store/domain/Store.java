package store.domain;

import static store.constants.Messages.NO_QUANTITY;

import store.domain.product.Product;
import store.domain.product.Product.Builder;

public class Store {
    private Receipt receipt;
    private final Storage storage;

    private Store(final Storage storage) {
        this.receipt = new Receipt();
        this.storage = storage;
    }

    public static Store from(final Storage storage) {
        return new Store(storage);
    }

    public void buyProduct(final Product product) {
        Product promotionProduct = storage.findPromotionProduct(product);
        if (promotionProduct != null && promotionProduct.checkPromotionDate()) {
            buyPromotionAndBasicProducts(product);
            return;
        }
        buyBasicProducts(product);
    }

    private void buyBasicProducts(final Product product) {
        Product basicProduct = findBasicProduct(product);

        receipt.addPurchaseProducts(basicProduct);
    }

    private void buyPromotionAndBasicProducts(final Product product) {
        Product promotionProduct = storage.findPromotionProduct(product);
        if (checkPromotionStock(promotionProduct, product)) { //  추가 구매 필요 X
            promotionProduct.reduceQuantity(product);
            updateReceipt(product, promotionProduct);
            return;
        }
//        promotionProduct.reduceQuantity(promotionProduct);

        Product basicProduct = findBasicProduct(product);
//        receipt.update(promotionProduct, basicProduct);
    }

    private void updateReceipt(Product product, Product promotionProduct) {
        receipt.addPurchaseProducts(promotionProduct.calculateBuyProduct(product));
        receipt.addAdditionalProducts(promotionProduct.calculateGetProduct(product));
    }

    private Product findPromotionProduct(final Product findProduct) {
        Product product = storage.findPromotionProduct(findProduct);
        if (checkPromotionStock(findProduct, product)) { //  추가 구매 필요 X
            product.reduceQuantity(findProduct);
            return new Builder(product.getName(), findProduct.getQuantity())
                    .price(product.getPrice()).promotion(product.getPromotion()).build();
        }
        // 추가 구매 필요
        product.reduceQuantity(product); // 제고 삭제

        return new Builder(product.getName(), product.getQuantity())
                .price(product.getPrice()).promotion(product.getPromotion()).build();
    }

    private Product findBasicProduct(final Product findProduct) {
        Product product = storage.findBasicProduct(findProduct);
        validateBasicStock(findProduct, product);
        product.reduceQuantity(findProduct);
        return new Builder(product.getName(), findProduct.getQuantity())
                .price(product.getPrice()).build();
    }

    private boolean checkPromotionStock(final Product promotionProduct, final Product product) {
        if (promotionProduct.getQuantity() >= product.getQuantity()) {
            return true;
        }
        return false;
    }

    private void validateBasicStock(final Product findProduct, final Product product) {
        if (product.getQuantity() < findProduct.getQuantity()) {
            throw new IllegalArgumentException(NO_QUANTITY.getErrorMessage());
        }
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Storage getStorage() {
        receipt = new Receipt();
        return storage;
    }
}
