package racingcar.service;

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
    
    private void playGame() {
        for (Car car : game.getCars()) {
            car.move(numberGenerator.generate());
        }
    }

    public Game getGame() {
        return game;
    }
}
