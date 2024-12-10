package lotto.util;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.Lotto;

public class NumbersSeparator {
    public static Lotto separateLottoNumbers(final List<Integer> numbers) {
        if (numbers.size() == 6) {
            return Lotto.createLotto(numbers);
        }
        List<Integer> lottoNumbers = new ArrayList<>(numbers);
        lottoNumbers.removeLast();
        return Lotto.createLotto(lottoNumbers);
    }

    public static int separateBonusNumber(final List<Integer> numbers) {
        return numbers.getLast();
    }
}
