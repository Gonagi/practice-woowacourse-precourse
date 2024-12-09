package racingcar.domain.random;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FakeRandomGeneratorTest {
    @MethodSource("invalidNumberTestCases")
    @ParameterizedTest(name = "입력 숫자: {0}")
    void 범위를_벗어난_숫자를_입력하면_오류가_발생한다(int number) {
        List<Integer> fakeNumbers = List.of(number);
        Assertions.assertThrows(IllegalArgumentException.class, () -> FakeRandomGenerator.from(fakeNumbers));
    }

    private static Stream<Arguments> invalidNumberTestCases() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(10),
                Arguments.of("-1"),
                Arguments.of("10")
        );
    }


}
