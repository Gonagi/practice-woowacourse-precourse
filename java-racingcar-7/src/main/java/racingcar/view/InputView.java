package racingcar.view;

import static racingcar.constants.Messages.INPUT_ATTEMPT_COUNT_MESSAGE;
import static racingcar.constants.Messages.INPUT_CAR_MESSAGE;
import static racingcar.constants.Messages.INPUT_NULL_ERROR;
import static racingcar.constants.Messages.INVALID_NUMBER_FROM;

import camp.nextstep.edu.missionutils.Console;
import java.util.Optional;
import java.util.regex.Pattern;

public class InputView {
    private static final String NUMBER_REGEX = "\\d+";

    public String inputCarNames() {
        System.out.println(INPUT_CAR_MESSAGE.getMessage());
        String carNames = Console.readLine();
        validateNull(carNames);
        return carNames;
    }

    public int inputAttemptCount() {
        System.out.println(INPUT_ATTEMPT_COUNT_MESSAGE.getMessage());
        String attemptCount = Console.readLine();
        validateNumber(attemptCount);
        return Integer.parseInt(attemptCount);
    }

    private void validateNull(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException(INPUT_NULL_ERROR.getMessage()));
    }

    private void validateNumber(final String attemptCount) {
        validateNull(attemptCount);
        if (!Pattern.matches(NUMBER_REGEX, attemptCount)) {
            throw new IllegalArgumentException(INVALID_NUMBER_FROM.getMessage());
        }
    }
}
