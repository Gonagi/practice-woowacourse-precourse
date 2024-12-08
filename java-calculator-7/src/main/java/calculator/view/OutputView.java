package calculator.view;

import calculator.constants.Messages;

public class OutputView {
    public void printResult(final int result) {
        System.out.println(Messages.RESULT_MESSAGE.getMessage() + result);
    }
}
