package store.view;

import static store.constants.Messages.ADDITIONAL_PURCHASE_MESSAGE;
import static store.constants.Messages.GET_MORE_PRODUCTS;
import static store.constants.Messages.INPUT_PRODUCT_NAME_AND_QUANTITY;
import static store.constants.Messages.INVALID_INPUT_FORMAT;
import static store.constants.Messages.INVALID_PURCHASE_FORMAT;
import static store.constants.Messages.MEMBERSHIP_MESSAGE;
import static store.constants.Messages.NO_APPLY_PROMOTION_PRODUCT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Optional;
import java.util.regex.Pattern;
import store.domain.Product;

public class InputView {
    private static final String PRODUCTS_INPUT_REGEX = "([[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+?-\\d],+)*([[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+?-\\d])";
    private static final String ANSWER_REGEX = "^[YN]$";

    public String inputProducts() {
        System.out.println(INPUT_PRODUCT_NAME_AND_QUANTITY.getMessage());
        String inputProducts = Console.readLine();
        validateProductsInput(inputProducts);
        return inputProducts;
    }

    public String inputGetMoreProducts(final Product product) {
        System.out.printf(GET_MORE_PRODUCTS.getMessage(), product.getName(), product.getQuantity());
        return inputAnswer();
    }

    public String inputPromotionApply(final Product product) {
        System.out.printf(NO_APPLY_PROMOTION_PRODUCT.getMessage(), product.getName(), product.getQuantity());
        return inputAnswer();
    }

    public String inputMemberShip() {
        System.out.println(MEMBERSHIP_MESSAGE.getMessage());
        return inputAnswer();
    }

    public String inputAdditionalPurchase() {
        System.out.println(ADDITIONAL_PURCHASE_MESSAGE.getMessage());
        return inputAnswer();
    }

    private String inputAnswer() {
        String answer = Console.readLine();
        validateAnswer(answer);
        return answer;
    }

    private void validateProductsInput(final String inputProducts) {
        validateNull(inputProducts);
        if (!Pattern.matches(PRODUCTS_INPUT_REGEX, inputProducts)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_FORMAT.getErrorMessage());
        }
    }

    private void validateAnswer(final String answer) {
        validateNull(answer);
        if (!Pattern.matches(ANSWER_REGEX, answer)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_FORMAT.getErrorMessage());
        }
    }

    private void validateNull(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage()));
    }
}
