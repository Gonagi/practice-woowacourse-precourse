package store.controller;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
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
            buyProductsProcess();

//            String getMoreProductsAnswer = retryOnException(() -> inputView.inputGetMoreProducts(product));
//            String promotionApplyAnswer = retryOnException(() -> inputView.inputPromotionApply(product));
            String membershipAnswer = retryOnException(inputView::inputMemberShip);

            outputView.printReceiptMessage(store.getReceipt(), membershipAnswer);

            String inputAdditionalPurchaseAnswer = retryOnException(inputView::inputAdditionalPurchase);
            if (Objects.equals(inputAdditionalPurchaseAnswer, "N")) {
                break;
            }
        }
    }

    private void buyProductsProcess() {
        retryOnException(() -> {
            String inputProducts = inputView.inputProducts();
            buyProducts(Translator.getProductsByStrings(inputProducts));
            return null;
        });
    }

    private void buyProducts(final List<Product> products) {
        for (Product product : products) {
            store.buyProduct(product);
        }
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
