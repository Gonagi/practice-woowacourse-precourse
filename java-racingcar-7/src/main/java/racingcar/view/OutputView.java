package racingcar.view;

import static racingcar.constants.Messages.RESULT_MESSAGE;
import static racingcar.constants.Messages.WINNER_MESSAGE;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {
    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE.getMessage());
    }

    public void printEachResult(final List<Car> cars) {
        for (Car car : cars) {
            System.out.printf("%s : %s\n", car.getName(), car.getLocation());
        }
        System.out.println();
    }

    public void printWinners() {
        System.out.print(WINNER_MESSAGE.getMessage());
    }
}
