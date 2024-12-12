package store.controller;

import java.util.List;
import java.util.function.Supplier;
import store.domain.Receipt;
import store.domain.Store;
import store.domain.product.Product;
import store.util.Translator;
import store.view.InputView;
import store.view.OutputView;

public class Controller {
    private final Store store;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final Store store, final InputView inputView, final OutputView outputView) {
        this.store = store;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            outputView.printStorageMessage(store.getStorage());
            String inputProducts = retryOnException(inputView::inputProducts);
            Receipt receipt = buyProducts(Translator.getProductsByStrings(inputProducts));

//            String membershipAnswer = retryOnException(inputView::inputMemberShip);
//            String getMoreProductsAnswer = retryOnException(() -> inputView.inputGetMoreProducts(product));
//            String promotionApplyAnswer = retryOnException(() -> inputView.inputPromotionApply(product));
//
//            String inputAdditionalPurchaseAnswer = retryOnException(inputView::inputAdditionalPurchase);
//
//            outputView.printReceiptMessage(store.getReceipt());
//
//            if (Objects.equals(inputAdditionalPurchaseAnswer, "Y")) {
//                break;
//            }
        }
    }


    private Receipt buyProducts(final List<Product> products) {
        for (Product product : products) {
            retryOnException(() -> {
                store.buyProduct(product);
                return null;
            });
        }
        return store.getReceipt();
    }

    private <T> T retryOnException(final Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
