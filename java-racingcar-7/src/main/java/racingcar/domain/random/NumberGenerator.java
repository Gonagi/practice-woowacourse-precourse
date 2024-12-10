package racingcar.domain.random;

import static racingcar.constants.Messages.INVALID_NUMBER_RANGE;

public interface NumberGenerator {
    int generate();

    default void validateNumber(final int number) {
        if (number < 0 || number > 9) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }
}
