package lotto.domain.lottoMachine;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FakeRandomGeneratorTest {
    @MethodSource("invalidRangeNumbersTestCases")
    @ParameterizedTest(name = "로또 번호: {0}, 보너스 번호: {1}")
    void 범위를_벗어난_번호로_로또를_생성하면_오류가_발생한다(List<Integer> invalidNumbers, int bonusNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                FakeRandomGenerator.of(invalidNumbers, bonusNumber));
    }

    @MethodSource("duplicateNumbersTestCases")
    @ParameterizedTest(name = "로또 번호: {0}, 보너스 번호: {1}")
    void 생성한_번호들간에_중복이_발생하면_오류가_발생한다(List<Integer> invalidNumbers, int bonusNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                FakeRandomGenerator.of(invalidNumbers, bonusNumber));
    }

    private static Stream<Arguments> invalidRangeNumbersTestCases() {
        return Stream.of(
                Arguments.of(List.of(0, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46), 7),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 0),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 46)
        );
    }

    private static Stream<Arguments> duplicateNumbersTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 3, 4, 5, 6), 7),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }
}
