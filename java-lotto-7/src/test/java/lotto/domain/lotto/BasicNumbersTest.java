package lotto.domain.lotto;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lottoMachine.FakeRandomGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasicNumbersTest {
    @MethodSource("invalidNumbersSizeTestCases")
    @ParameterizedTest(name = "로또 번호: {0}")
    void 생성한_기본_번호의_개수가_맞지_않으면_오류가_발생한다(List<Integer> invalidNumbers) {
        List<Integer> fakedNumbers = FakeRandomGenerator.from(invalidNumbers).generate();

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BasicNumbers.from(fakedNumbers);
        });
    }

    private static Stream<Arguments> invalidNumbersSizeTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of())
        );
    }
}
