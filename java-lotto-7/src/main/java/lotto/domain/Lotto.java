package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto createLotto(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public List<Integer> getLottoNumbers() {
        ArrayList<Integer> lottoNumbers = new ArrayList<>(numbers);
        lottoNumbers.removeLast();
        return lottoNumbers;
    }

    public int getBonusNumber() {
        return numbers.getLast();
    }
}
