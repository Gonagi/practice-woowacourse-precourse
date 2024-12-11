package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {
    @MethodSource("resultTestCases")
    @ParameterizedTest(name = "기본 번호 맞춘 개수: {0}, 보너스 번호 맞춤 여부: {1}, 등수: {2}")
    void 결과_정상_흐름(int matchBasicCount, boolean matchBonus, Result rank) {
        Assertions.assertThat(Result.of(matchBasicCount, matchBonus))
                .isEqualTo(rank);
    }

    @Test
    void 백분율_계산기_정상_흐름() {
        String expectedReturnOfRate = "50000125.0";
        List<Result> results = new ArrayList<>(List.of(Result.FIRST, Result.FIFTH, Result.NOTHING, Result.NOTHING));

        Assertions.assertThat(Result.calculateRateOfReturn(results))
                .isEqualTo(expectedReturnOfRate);
    }

    private static Stream<Arguments> resultTestCases() {
        return Stream.of(
                Arguments.of(6, false, Result.FIRST),
                Arguments.of(5, true, Result.SECOND),
                Arguments.of(5, false, Result.THIRD),
                Arguments.of(4, true, Result.FOURTH),
                Arguments.of(4, false, Result.FOURTH),
                Arguments.of(3, true, Result.FIFTH),
                Arguments.of(3, false, Result.FIFTH),
                Arguments.of(2, true, Result.NOTHING),
                Arguments.of(2, false, Result.NOTHING),
                Arguments.of(1, true, Result.NOTHING),
                Arguments.of(1, false, Result.NOTHING),
                Arguments.of(0, true, Result.NOTHING),
                Arguments.of(0, false, Result.NOTHING)
        );
    }
}
