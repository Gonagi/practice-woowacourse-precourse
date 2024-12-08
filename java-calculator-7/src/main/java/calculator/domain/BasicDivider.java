package calculator.domain;

import static calculator.constants.Constants.BASIC_REGEX;

import java.util.List;

public class BasicDivider implements Divider {
    @Override
    public List<Integer> separate(String input) {
        return parseNumbers(input, BASIC_REGEX);
    }
}
