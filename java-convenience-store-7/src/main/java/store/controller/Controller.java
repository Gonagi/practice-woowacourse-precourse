package store.controller;

import static store.util.ExceptionRetryHandler.retryOnException;

import java.util.List;
import java.util.Objects;
import store.domain.product.Product;
import store.service.StoreService;
import store.util.Translator;
import store.view.InputView;
import store.view.OutputView;

public class Controller {
    private final StoreService storeService;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final StoreService storeService, final InputView inputView, final OutputView outputView) {
        this.storeService = storeService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            outputView.printStorageMessage(storeService.getStorage());
            buyProductsProcess();

//            String getMoreProductsAnswer = retryOnException(() -> inputView.inputGetMoreProducts(product));
//            String promotionApplyAnswer = retryOnException(() -> inputView.inputPromotionApply(product));
            String membershipAnswer = retryOnException(inputView::inputMemberShip);

            outputView.printReceiptMessage(storeService.getReceipt(), membershipAnswer);

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
            storeService.buyProduct(product);
        }
    }
}
