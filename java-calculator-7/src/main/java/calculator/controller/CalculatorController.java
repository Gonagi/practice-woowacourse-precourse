package calculator.controller;

import calculator.domain.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final Calculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(final Calculator calculator, final InputView inputView,
                                final OutputView outputView) {
        this.calculator = calculator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String input = inputView.input();
        outputView.printResult(calculator.calculate(input));
    }
}
