package lotto.domain.lotto;

import static lotto.constants.Messages.INVALID_LOTTO_SIZE;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        validateLottoSize(numbers);
        this.numbers = numbers;
    }

    public static Lotto createLotto(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public List<Integer> getLottoNumbers() {
        return numbers;
    }

    private void validateLottoSize(final List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage());
        }
    }
}
