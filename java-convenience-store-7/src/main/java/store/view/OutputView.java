package store.view;

import static store.constants.Messages.BASIC_PRODUCTS_STOCK_MESSAGE;
import static store.constants.Messages.BASIC_PRODUCTS_WITH_NO_STOCK_MESSAGE;
import static store.constants.Messages.PROMOTION_PRODUCTS_STOCK_MESSAGE;
import static store.constants.Messages.START_MESSAGE;

import store.domain.Receipt;
import store.domain.Storage;
import store.domain.product.Product;

public class OutputView {
    public void printStorageMessage(final Storage storage) {
        System.out.println(START_MESSAGE.getMessage());
        for (Product product : storage.getProducts()) {
            printProduct(product);
        }
    }

    public void printReceiptMessage(final Receipt receipt) {
        printPurchaseReceipt(receipt);
        printAdditonalRecipt(receipt);
        printMoneyRecipt(receipt);
        System.out.println();
    }

    private void printMoneyRecipt(final Receipt receipt) {
        System.out.println("====================================");
        System.out.printf("총구매액\t\t%d\t%,d", receipt.getPurchaseProductsCount(), receipt.getPurchaseProductsPrice());
        System.out.printf("행사할인\t\t\t-%,d", receipt.getAdditionalProductsPrice());
        System.out.printf("멤버십할인\t\t\t-%,d", receipt.getMembershipPrice());
        System.out.printf("내실돈\t\t\t %,d", receipt.getTotalPrice());
    }

    private void printAdditonalRecipt(final Receipt receipt) {
        System.out.println("=============증\t정===============");
        for (Product product : receipt.getAdditionalProducts()) {
            System.out.printf("%s\t\t%d", product.getName(), product.getQuantity());
        }
    }

    private void printPurchaseReceipt(final Receipt receipt) {
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
        for (Product product : receipt.getPurchaseProducts()) {
            System.out.printf("%s\t\t%d \t%,d", product.getName(), product.getQuantity(),
                    product.getQuantity() * product.getPrice());
        }
    }

    private void printProduct(Product product) {
        if (product.getPromotion() != null) {
            printPromotionProduct(product);
            return;
        }
        if (product.getQuantity() == 0) {
            printBasicProductWithNoStock(product);
            return;
        }
        printBasicProduct(product);
    }

    private void printBasicProduct(Product product) {
        System.out.printf(BASIC_PRODUCTS_STOCK_MESSAGE.getMessage(), product.getName(),
                product.getPrice(), product.getQuantity());
    }

    private void printBasicProductWithNoStock(Product product) {
        System.out.printf(BASIC_PRODUCTS_WITH_NO_STOCK_MESSAGE.getMessage(), product.getName(),
                product.getPrice());
    }

    private void printPromotionProduct(Product product) {
        System.out.printf(PROMOTION_PRODUCTS_STOCK_MESSAGE.getMessage(), product.getName(), product.getPrice(),
                product.getQuantity(), product.getPromotionName());
    }
}
