package lotto.domain.random;

import static lotto.constants.Messages.DUPLICATE_LOTTO_NUMBER;
import static lotto.constants.Messages.INVALID_LOTTO_NUMBER_RANGE;
import static lotto.constants.Messages.INVALID_LOTTO_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface LottoMachine {
    List<Integer> generate();

    default void validateLottoNumbers(final List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoRange(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateLottoSize(final List<Integer> numbers) {
        if (numbers.size() != 7) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateLottoRange(final List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE.getMessage());
            }
        }
    }

    private void validateDuplicateNumber(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
            }
        }
    }
}
