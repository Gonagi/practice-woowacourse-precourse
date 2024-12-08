package calculator.domain;

import calculator.constants.Messages;
import java.util.ArrayList;
import java.util.List;

public interface Divider {
    List<Integer> separate(final String input);

    default List<Integer> parseNumbers(final String numbersPart, final String formatPattern) {
        String[] numbers = numbersPart.split(formatPattern);

        List<Integer> result = new ArrayList<>();
        for (String number : numbers) {
            try {
                result.add(Integer.parseInt(number.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(Messages.OUT_OF_NUMERIC_FORM.getMessage() + e);
            }
        }

        return result;
    }
}
