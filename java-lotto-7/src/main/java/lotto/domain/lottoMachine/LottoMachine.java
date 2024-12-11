package lotto.domain.lottoMachine;

import static lotto.constants.Messages.DUPLICATE_LOTTO_NUMBER;
import static lotto.constants.Messages.INVALID_LOTTO_NUMBER_RANGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface LottoMachine {
    List<Integer> generate();

    default void validateLottoNumbers(final List<Integer> numbers) {
        validateLottoRange(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateLottoRange(final List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE.getErrorMessage());
            }
        }
    }

    private void validateDuplicateNumber(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getErrorMessage());
            }
        }
    }
}
