package store.view;

import static store.constants.Messages.BASIC_PRODUCTS_STOCK_MESSAGE;
import static store.constants.Messages.BASIC_PRODUCTS_WITH_NO_STOCK_MESSAGE;
import static store.constants.Messages.PROMOTION_PRODUCTS_STOCK_MESSAGE;
import static store.constants.Messages.START_MESSAGE;

import store.domain.Product;
import store.domain.Receipt;
import store.domain.Storage;

public class OutputView {
    /*
    환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다
재고가 0개라면 재고없음을 출력한다
부족한 수량에 대한 추가 여부 질문을 한다
프로모션 재고 부족으로 인해 일부 수량에 대해 정가로 결제할지 여부를 질문한다
멤버십 할인 적용 여부를 질문한다
구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다
추가 구매 여부에 대한 질문을 한다
     */
    public void printStorageMessage(final Storage storage) {
        System.out.println(START_MESSAGE.getMessage());
        for (Product product : storage.getProducts()) {
            printProduct(product);
        }
        System.out.println();
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
