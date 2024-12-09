package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.random.RealRandomGenerator;
import racingcar.service.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameService gameService = createGame();
        playGames(gameService);
        quitGame(gameService);
    }

    private GameService createGame() {
        String carNames = inputView.inputCarNames();
        int attemptCount = inputView.inputAttemptCount();
        Game game = Game.generateGame(carNames, attemptCount);
        return GameService.of(game, new RealRandomGenerator());
    }

    private void playGames(GameService gameService) {
        outputView.printResultMessage();
        for (int count = 0; count < gameService.getAttemptCount(); count++) {
            gameService.playGame();
            outputView.printEachResult(gameService.getCars());
        }
    }

    private void quitGame(GameService gameService) {
        outputView.printWinners();
        List<Car> winnerCars = gameService.createWinnerCars();
        String result = String.join(", ", winnerCars.stream()
                .map(Car::getName)
                .toList());
        System.out.println(result);
    }
}
