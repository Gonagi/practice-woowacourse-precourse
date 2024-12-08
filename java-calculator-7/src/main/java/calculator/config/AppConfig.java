package calculator.config;

import calculator.controller.CalculatorController;
import calculator.domain.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class AppConfig {
    private final Calculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public AppConfig() {
        this.calculator = calculator();
        this.inputView = inputView();
        this.outputView = outputView();
    }

    public CalculatorController calculatorController() {
        return new CalculatorController(calculator, inputView, outputView);
    }

    private Calculator calculator() {
        return new Calculator();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
