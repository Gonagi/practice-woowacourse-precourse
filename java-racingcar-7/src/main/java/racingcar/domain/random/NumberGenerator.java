package racingcar.domain.random;

public interface NumberGenerator {
    int generate();

    default void validateNumber(final int number) {
        if (number < 0 || number > 9) {
            throw new IllegalArgumentException("범위를 벗어나는 숫자를 입력했습니다.");
        }
    }
}
