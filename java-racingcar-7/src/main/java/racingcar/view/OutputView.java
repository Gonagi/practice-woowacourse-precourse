package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {
    public void printResultMessage() {
        System.out.println("실행 결과");
    }

    public void printEachResult(final List<Car> cars) {
        for (Car car : cars) {
            System.out.printf("%s : %s\n", car.getName(), car.getLocation());
        }
        System.out.println();
    }

    public void printWinners() {
        System.out.print("최종 우승자 : ");
    }
}
