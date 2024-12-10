package racingcar.domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        String carNames = "pobi,woni,jun";
        int attemptCount = 5;

        game = Game.generateGame(carNames, attemptCount);
    }

    @Test
    void 게임_생성_정상_흐름() {
        List<Car> expectedCars = List.of(
                Car.of("pobi"),
                Car.of("woni"),
                Car.of("jun")
        );
        int expectedAttemptCount = 5;

        Assertions.assertThat(game.getCars())
                .isEqualTo(expectedCars);
        Assertions.assertThat(game.getAttemptCount())
                .isEqualTo(expectedAttemptCount);
    }

    @Test
    void 자동차_이름에_중복이_있으면_오류가_발생한다() {
        String carNames = "pobi,pobi";
        int attemptCount = 5;

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> Game.generateGame(carNames, attemptCount));
    }
    
    @MethodSource("invalidCarNamesTestCases")
    @ParameterizedTest(name = "입력 문자열: {0}")
    void 자동차_이름_문자열에_알파벳_콤마가_아닌_다른_문자가_있을때는_오류가_발생한다(String carNames) {
        int attemptCount = 5;

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> Game.generateGame(carNames, attemptCount));
    }

    private static Stream<Arguments> invalidCarNamesTestCases() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("pobi1"),
                Arguments.of("pobi woni jun"),
                Arguments.of("pobi&woni&jun")
        );
    }
}
