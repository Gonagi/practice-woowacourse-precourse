package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Game {
    private static final String COMMA = ",";
    private static final String ALPHABET_COMMA_REGEX = "([a-zA-Z]+,)*[a-zA-Z]+";
    private final List<Car> cars;
    private final int attemptCount;

    private Game(final List<Car> cars, final int attemptCount) {
        this.cars = cars;
        this.attemptCount = attemptCount;
    }

    public static Game generateGame(final String carNames, final int attemptCount) {
        List<Car> cars = getCarList(carNames);
        return new Game(cars, attemptCount);
    }

    private static List<Car> getCarList(String carNames) {
        validateCarNames(carNames);
        String[] names = carNames.split(COMMA);

        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(Car.of(name));
        }

        return cars;
    }

    private static void validateCarNames(final String carNames) {
        if (!Pattern.matches(ALPHABET_COMMA_REGEX, carNames)) {
            throw new IllegalArgumentException("입력한 자동차 이름들의 형식이 맞지 않습니다.");
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}