package lotto.view;

import static lotto.constants.Messages.PRINT_FIFTH_MESSAGE;
import static lotto.constants.Messages.PRINT_FIRST_MESSAGE;
import static lotto.constants.Messages.PRINT_FOURTH_MESSAGE;
import static lotto.constants.Messages.PRINT_LOTTO_RESULTS_MESSAGE;
import static lotto.constants.Messages.PRINT_RATE_OF_RANGE_MESSAGE;
import static lotto.constants.Messages.PRINT_SECOND_MESSAGE;
import static lotto.constants.Messages.PRINT_THIRD_MESSAGE;
import static lotto.constants.Messages.PURCHASED_MESSAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Result;
import lotto.domain.lotto.BasicNumbers;

public class OutputView {

    public void printPurchaseLottos(final List<BasicNumbers> basicNumbers) {
        System.out.printf(PURCHASED_MESSAGE.getMessage(), basicNumbers.size());

        for (BasicNumbers basicNumber : basicNumbers) {
            List<Integer> lotto = new ArrayList<>(basicNumber.getBasicNumbers());
            Collections.sort(lotto);
            System.out.printf("%s\n", lotto);
        }
    }

    public void printLottoResults(final List<Result> results) {
        System.out.println(PRINT_LOTTO_RESULTS_MESSAGE.getMessage());
        System.out.printf(PRINT_FIFTH_MESSAGE.getMessage(), Collections.frequency(results, Result.FIFTH));
        System.out.printf(PRINT_FOURTH_MESSAGE.getMessage(), Collections.frequency(results, Result.FOURTH));
        System.out.printf(PRINT_THIRD_MESSAGE.getMessage(), Collections.frequency(results, Result.THIRD));
        System.out.printf(PRINT_SECOND_MESSAGE.getMessage(), Collections.frequency(results, Result.SECOND));
        System.out.printf(PRINT_FIRST_MESSAGE.getMessage(), Collections.frequency(results, Result.FIRST));
    }

    public void printRateOfRange(final String rateOfRange) {
        System.out.printf(PRINT_RATE_OF_RANGE_MESSAGE.getMessage(), rateOfRange);
    }
}
