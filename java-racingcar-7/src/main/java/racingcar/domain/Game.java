package racingcar.domain;

import static racingcar.constants.Messages.INVALID_CAR_NAME_FORM;
import static racingcar.constants.Messages.INVALID_CAR_NAME_LENGTH;

import java.util.Arrays;
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

    public List<String> getCarLocations() {
        return cars.stream()
                .map(Car::getLocation)
                .toList();
    }

    private static List<Car> getCarList(String carNames) {
        validateCarNames(carNames);
        String[] names = carNames.split(COMMA);

        return Arrays.stream(names)
                .map(String::trim)
                .peek(Game::validateNameLength)
                .map(Car::of)
                .toList();
    }

    private static void validateNameLength(final String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException(INVALID_CAR_NAME_LENGTH.getMessage());
        }
    }

    private static void validateCarNames(final String carNames) {
        if (!Pattern.matches(ALPHABET_COMMA_REGEX, carNames)) {
            throw new IllegalArgumentException(INVALID_CAR_NAME_FORM.getMessage());
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
