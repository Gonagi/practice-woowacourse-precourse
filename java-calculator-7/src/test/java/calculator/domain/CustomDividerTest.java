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

class CustomDividerTest {
    Divider customDivider;

    @BeforeEach
    void setUp() {
        customDivider = new CustomDivider();
    }

    @Test
    void 문자열을_개행문자로_나눈다() {
        String input = "123\\n456";
        Assertions.assertDoesNotThrow(() -> customDivider.separate(input));
    }

    @Test
    void 구분자_문자열이_비어있습니다() {
        String input = "//\\n456";
        Assertions.assertThrows(IllegalArgumentException.class, () -> customDivider.separate(input));
    }

    @MethodSource("numberInFrontPartTestCases")
    @ParameterizedTest(name = "입력 문자열: {0}")
    void 구분자에는_숫자가_올_수_없습니다(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> customDivider.separate(input));
    }

    @MethodSource("invalidInputTestCases")
    @ParameterizedTest(name = "입력 문자열: {0}, 오류 이유: {1}")
    void 계산식_내_숫자_형식이_아닌_문자가_있습니다(String input, String reason) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> customDivider.separate(input));
    }

    @Test
    void 커스텀_구분자의_정상흐름() {
        String input = "//a\\n1a2,3";
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3));

        org.assertj.core.api.Assertions.assertThat(customDivider.separate(input))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> invalidInputTestCases() {
        return Stream.of(
                Arguments.of("//;\n1!2,3:4,5", "계산식 내 구분자외 다른 문자 입력"),
                Arguments.of("//abc\n1ab2,3", "계산식 내 구분자 오류"),
                Arguments.of("//;\na,2;3", "계산식 내 숫자가 아닌 문자 입력")
        );
    }

    private static Stream<Arguments> numberInFrontPartTestCases() {
        return Stream.of(
                Arguments.of("//1\\n"),
                Arguments.of("//a1b\\n")
        );
    }
}
