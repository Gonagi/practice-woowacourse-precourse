package racingcar.service;

import java.util.List;
import java.util.Objects;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.random.NumberGenerator;

public class GameService {
    private final Game game;
    private final NumberGenerator numberGenerator;

    private GameService(final Game game, final NumberGenerator numberGenerator) {
        this.game = game;
        this.numberGenerator = numberGenerator;
    }

    public static GameService of(final Game game, final NumberGenerator numberGenerator) {
        return new GameService(game, numberGenerator);
    }

    public void playGames() {
        for (int count = 0; count < game.getAttemptCount(); count++) {
            playGame();
        }
    }

    public List<Car> createWinnerCars() {
        int maxIndex = getMaxIndex();
        return game.getCars().stream().filter(car -> Objects.equals(car.getLocationIndex(), maxIndex))
                .toList();
    }

    public List<Car> getCars() {
        return game.getCars();
    }

    private void playGame() {
        for (Car car : game.getCars()) {
            car.move(numberGenerator.generate());
        }
    }

    private int getMaxIndex() {
        return game.getCars().stream().mapToInt(Car::getLocationIndex).max()
                .orElseThrow(() -> new IllegalArgumentException("게임에 참가한 자동차가 없습니다."));
    }

    public Game getGame() {
        return game;
    }
}
