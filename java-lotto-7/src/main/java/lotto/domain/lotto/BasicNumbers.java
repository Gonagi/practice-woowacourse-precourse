package lotto.domain.lotto;

import static lotto.constants.Messages.INVALID_LOTTO_SIZE;

import java.util.List;

public class BasicNumbers {
    private final List<Integer> numbers;

    private BasicNumbers(final List<Integer> numbers) {
        validateBasicNumbersSize(numbers);
        this.numbers = numbers;
    }

    public static BasicNumbers from(final List<Integer> numbers) {
        return new BasicNumbers(numbers);
    }

    public List<Integer> getBasicNumbers() {
        return numbers;
    }

    private void validateBasicNumbersSize(final List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage());
        }
    }
}
