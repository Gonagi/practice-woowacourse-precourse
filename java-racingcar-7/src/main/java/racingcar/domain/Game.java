package racingcar.domain;

import static racingcar.constants.Messages.DUPLICATE_CAR_NAMES;
import static racingcar.constants.Messages.INVALID_CAR_NAME_FORM;
import static racingcar.constants.Messages.NON_POSITIVE_ATTEMPT_COUNT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        validateAttemptCount(attemptCount);
        return new Game(cars, attemptCount);
    }

    public List<String> getCarLocations() {
        return cars.stream()
                .map(Car::getLocation)
                .toList();
    }

    public static void validateAttemptCount(final int attemptCount) {
        if (attemptCount < 0) {
            throw new IllegalArgumentException(NON_POSITIVE_ATTEMPT_COUNT.getMessage());
        }
    }

    private static List<Car> getCarList(String carNames) {
        validateCarNames(carNames);
        String[] names = carNames.split(COMMA);

        validateDuplicateName(names);

        return Arrays.stream(names)
                .map(String::trim)
                .peek(Car::validateNameLength)
                .map(Car::of)
                .toList();
    }

    private static void validateDuplicateName(final String[] names) {
        Set<String> uniqueNames = new HashSet<>();

        for (String name : names) {
            if (!uniqueNames.add(name.trim())) {
                throw new IllegalArgumentException(DUPLICATE_CAR_NAMES.getMessage() + name.trim());
            }
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
