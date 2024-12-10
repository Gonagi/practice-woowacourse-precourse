package racingcar.domain.random;

import static racingcar.constants.Messages.INVALID_NUMBER_COUNT;

import java.util.List;

public class FakeRandomGenerator implements NumberGenerator {
    private final List<Integer> numbers;
    private int index = 0;

    private FakeRandomGenerator(final List<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumber(number);
        }
        this.numbers = numbers;
    }

    public static FakeRandomGenerator from(final List<Integer> numbers) {
        return new FakeRandomGenerator(numbers);
    }

    @Override
    public int generate() {
        if (index >= numbers.size()) {
            throw new IllegalArgumentException(INVALID_NUMBER_COUNT.getMessage());
        }
        return numbers.get(index++);
    }
}
