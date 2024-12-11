package lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;

public class NumbersSeparator {
    private static final String COMMA = ",";

    public static Lotto separateLottoNumbers(final List<Integer> numbers) {
        if (numbers.size() == 6) {
            return Lotto.from(numbers);
        }
        List<Integer> lottoNumbers = new ArrayList<>(numbers);
        lottoNumbers.removeLast();
        return Lotto.from(lottoNumbers);
    }

    public static BonusNumber separateBonusNumber(final List<Integer> numbers) {
        return BonusNumber.from(numbers.getLast());
    }

    public static List<Integer> splitWinningLottoInput(final String winningBasicNumbers) {
        String[] basicNumbers = winningBasicNumbers.split(COMMA);
        return Arrays.stream(basicNumbers)
                .map(Integer::parseInt)
                .toList();
    }
}
