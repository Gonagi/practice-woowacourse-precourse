package calculator.domain;

import static calculator.constants.Constants.BASIC_REGEX;

import calculator.constants.Messages;
import java.util.List;
import java.util.regex.Pattern;

public class CustomDivider implements Divider {
    private static final String SPLIT_STRING = "\\\\n";
    private static final String NUMBER_REGEX = "[0-9]";

    @Override
    public List<Integer> separate(final String input) {
        String[] parts = validateCustomInput(input);
        String customDelimiter = extractCustomSeparator(parts[0]);
        String numbersPart = parts[1];
        String formatPattern = createCombinedPattern(customDelimiter);

        return parseNumbers(numbersPart, formatPattern);
    }

    private String[] validateCustomInput(final String input) {
        String[] parts = input.split(SPLIT_STRING, 2);
        validateFrontPart(parts);
        validateBackPart(parts);
        return parts;
    }

    private void validateFrontPart(final String[] parts) {
        if (Pattern.matches(NUMBER_REGEX, parts[0])) {
            throw new IllegalArgumentException(Messages.NUMBER_IN_FRONT_INPUT.getMessage());
        }
    }

    private void validateBackPart(final String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException(Messages.INVALID_AT_BACK_INPUT.getMessage());
        }
    }

    private String extractCustomSeparator(final String separatorPart) {
        if (separatorPart.length() <= 2) {
            throw new IllegalArgumentException(Messages.INVALID_AT_FRONT_INPUT.getMessage());
        }
        return separatorPart.substring(2);
    }

    private String createCombinedPattern(final String customDelimiter) {
        return String.format("%s|%s", BASIC_REGEX, customDelimiter);
    }
}
