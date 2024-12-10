package lotto.domain.lottoMachine;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomGenerator implements LottoMachine {
    private final List<Integer> numbers;

    private FakeRandomGenerator(final List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    public static FakeRandomGenerator from(final List<Integer> numbers) {
        return new FakeRandomGenerator(numbers);
    }
    
    public static FakeRandomGenerator of(final List<Integer> numbers, final int bonusNumber) {
        ArrayList<Integer> additionNumbers = new ArrayList<>(numbers);
        additionNumbers.add(bonusNumber);
        return new FakeRandomGenerator(additionNumbers);
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}
