package calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasicDividerTest {
    Divider basicDivider;

    @BeforeEach
    void setUp() {
        basicDivider = new BasicDivider();
    }

    @MethodSource("invalidInputTestCases")
    @ParameterizedTest(name = "입력 문자열: {0}")
    void 계산식_내_숫자_형식이_아닌_문자가_있습니다(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> basicDivider.separate(input));
    }

    @Test
    void 기본_구분자의_정상흐름() {
        String input = "1,2:3";
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3));

        org.assertj.core.api.Assertions.assertThat(basicDivider.separate(input))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> invalidInputTestCases() {
        return Stream.of(
                Arguments.of("1aa2,3"),
                Arguments.of("1 2 3")
        );
    }
}
