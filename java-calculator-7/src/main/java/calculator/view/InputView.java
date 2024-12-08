package calculator.view;

import calculator.constants.Messages;
import camp.nextstep.edu.missionutils.Console;
import java.util.Optional;

public class InputView {
    public String input() {
        System.out.println(Messages.INPUT_MESSAGE.getMessage());
        String input = Console.readLine();
        validate(input);
        return input;
    }

    private void validate(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException(Messages.INPUT_NULL_ERROR.getMessage()));
    }
}
