package calculator.domain;

import calculator.constants.Messages;
import java.util.List;

public class Calculator {
    public int calculate(final String input) {
        Divider divider = DividerFactory.of(input);
        List<Integer> numbers = divider.separate(input);

        return numbers.stream()
                .peek(number -> {
                    if (number < 0) {
                        throw new IllegalArgumentException(Messages.NON_POSITIVE_NUMBER.getMessage());
                    }
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}
