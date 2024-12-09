package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Optional;
import java.util.regex.Pattern;

public class InputView {
    private static final String NUMBER_REGEX = "\\d+";

    public String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNames = Console.readLine();
        validateNull(carNames);
        return carNames;
    }

    public int inputAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String attemptCount = Console.readLine();
        validateNumber(attemptCount);
        return Integer.parseInt(attemptCount);
    }

    private void validateNull(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("null을 입력하셨습니다."));
    }

    private void validateNumber(final String attemptCount) {
        validateNull(attemptCount);
        if (!Pattern.matches(NUMBER_REGEX, attemptCount)) {
            throw new IllegalArgumentException("잘못된 형식의 숫자를 입력했습니다.");
        }
    }
}
