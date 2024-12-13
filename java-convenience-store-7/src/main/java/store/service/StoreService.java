package store.service;

import static store.constants.Messages.GET_MORE_PRODUCTS;
import static store.constants.Messages.NO_QUANTITY;

import store.domain.Receipt;
import store.domain.Storage;
import store.domain.Store;
import store.domain.product.Product;
import store.domain.product.Product.Builder;
import store.view.InputView;

public class StoreService {
    private final Store store;
    private final InputView inputView;

    public StoreService(final Store store, final InputView inputView) {
        this.store = store;
        this.inputView = inputView;
    }

    public void buyProduct(final Product product) {
        Product promotionProduct = getStorage().findPromotionProduct(product);
        if (promotionProduct != null && promotionProduct.checkPromotionDate()) {

            buyPromotionAndBasicProducts(product);
            return;
        }
        buyBasicProducts(product);
    }

    public Receipt getReceipt() {
        return store.getReceipt();
    }

    public Storage getStorage() {
        return store.getStorage();
    }

    private void buyBasicProducts(final Product product) {
        Product basicProduct = findBasicProduct(product);

        getReceipt().addPurchaseProducts(basicProduct);
    }

    private void buyPromotionAndBasicProducts(final Product product) {
        Product promotionProduct = getStorage().findPromotionProduct(product);
        if (promotionProduct.calculateBuyQuantity(product) == product.getQuantity()) {
            throw new IllegalArgumentException(String.format(GET_MORE_PRODUCTS.getMessage(),
                    product.getName(), product.getPromotionGet()));
        }

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
        getReceipt().addPurchaseProducts(promotionProduct.calculateBuyProduct(product));
        getReceipt().addAdditionalProducts(promotionProduct.calculateGetProduct(product));
    }

    private Product findPromotionProduct(final Product findProduct) {
        Product product = getStorage().findPromotionProduct(findProduct);
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
        Product product = getStorage().findBasicProduct(findProduct);
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
}
