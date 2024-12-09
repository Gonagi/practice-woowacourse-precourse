package racingcar.domain.random;

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
            throw new IllegalArgumentException("fakeNumber 개수가 맞지 않습니다.");
        }
        return numbers.get(index++);
    }
}
