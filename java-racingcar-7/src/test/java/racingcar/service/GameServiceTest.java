package racingcar.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.random.FakeRandomGenerator;
import racingcar.domain.random.NumberGenerator;

class GameServiceTest {
    Game game;
    NumberGenerator fakeRandomGenerator;

    @BeforeEach
    void setUp() {
        String carNames = "pobi,woni,jun";
        int attemptCount = 3;
        game = Game.generateGame(carNames, attemptCount);

        List<Integer> fakeNumbers = List.of(4, 4, 0, 7, 6, 9, 5, 8, 1);
        fakeRandomGenerator = FakeRandomGenerator.from(fakeNumbers);
    }

    @Test
    void 게임_플레이_정상_흐름() {
        List<String> expectedLocations = List.of("---", "---", "-");

        GameService gameService = GameService.of(game, fakeRandomGenerator);
        for (int count = 0; count < gameService.getAttemptCount(); count++) {
            gameService.playGame();
        }

        Assertions.assertThat(gameService.getGame().getCarLocations())
                .isEqualTo(expectedLocations);
    }

    @Test
    void 우승자_생성_정상_흐름() {
        List<Car> expectedWinners = List.of(Car.of("pobi"), Car.of("woni"));

        GameService gameService = GameService.of(game, fakeRandomGenerator);
        for (int count = 0; count < gameService.getAttemptCount(); count++) {
            gameService.playGame();
        }
        List<Car> winnerCars = gameService.createWinnerCars();

        Assertions.assertThat(winnerCars).isEqualTo(expectedWinners);
    }
}
