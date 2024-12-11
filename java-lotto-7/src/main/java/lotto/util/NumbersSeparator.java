package lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.BasicNumbers;
import lotto.domain.lotto.BonusNumber;

public class NumbersSeparator {
    private static final String COMMA = ",";

    public static BasicNumbers separateLottoNumbers(final List<Integer> numbers) {
        if (numbers.size() == 6) {
            return BasicNumbers.from(numbers);
        }
        List<Integer> lottoNumbers = new ArrayList<>(numbers);
        lottoNumbers.removeLast();
        return BasicNumbers.from(lottoNumbers);
    }

    public static BonusNumber separateBonusNumber(final List<Integer> numbers) {
        return BonusNumber.from(numbers.getLast());
    }

    public static List<Integer> splitWinningBasicNumbers(final String winningBasicNumbers) {
        String[] basicNumbers = winningBasicNumbers.split(COMMA);
        return Arrays.stream(basicNumbers)
                .map(Integer::parseInt)
                .toList();
    }
}
