package lotto.view;

import static lotto.constants.Messages.INPUT_BASIC_NUMBERS;
import static lotto.constants.Messages.INPUT_BONUS_NUMBER;
import static lotto.constants.Messages.INPUT_MONEY_MESSAGE;
import static lotto.constants.Messages.INPUT_NULL_ERROR;
import static lotto.constants.Messages.INVALID_NUMBER_FROM;

import camp.nextstep.edu.missionutils.Console;
import java.util.Optional;
import java.util.regex.Pattern;

public class Inputview {
    private static final String NUMBER_REGEX = "\\d+";
    private static final String LOTTO_NUMBER_REGEX = "([\\d]+,)*[\\d]+";

    public int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE.getMessage());
        return inputNumber();
    }

    public String inputWinnerBasicNumbers() {
        System.out.println(INPUT_BASIC_NUMBERS.getMessage());
        String winningBasicNumbers = Console.readLine();
        validateBasicNumbers(winningBasicNumbers);
        return winningBasicNumbers;
    }

    public int inputWinningBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER.getMessage());
        return inputNumber();
    }

    private int inputNumber() {
        String inputBonusNumber = Console.readLine();
        validateNumber(inputBonusNumber);
        return Integer.parseInt(inputBonusNumber);
    }

    private void validateNull(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException(INPUT_NULL_ERROR.getErrorMessage()));
    }

    private void validateBasicNumbers(final String basicNumbers) {
        validateNull(basicNumbers);
        if (!Pattern.matches(LOTTO_NUMBER_REGEX, basicNumbers)) {
            throw new IllegalArgumentException(INVALID_NUMBER_FROM.getErrorMessage());
        }
    }

    private void validateNumber(final String inputMoney) {
        validateNull(inputMoney);
        if (!Pattern.matches(NUMBER_REGEX, inputMoney)) {
            throw new IllegalArgumentException(INVALID_NUMBER_FROM.getErrorMessage());
        }
    }
}
