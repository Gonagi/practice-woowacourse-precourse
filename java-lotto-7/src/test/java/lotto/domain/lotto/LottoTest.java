package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lottoMachine.FakeRandomGenerator;
import lotto.util.NumbersSeparator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            List<Integer> fakeLottoNumbers = FakeRandomGenerator.from(List.of(1, 2, 3, 4, 5, 6, 7)).generate();
            Lotto.createLotto(fakeLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호를_반환한다() {
        List<Integer> expectedLottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> fakedLottoNumbers = FakeRandomGenerator.of(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        Lotto lotto = NumbersSeparator.separateLottoNumbers(fakedLottoNumbers);

        Assertions.assertThat(lotto.getLottoNumbers())
                .isEqualTo(expectedLottoNumbers);
    }

    @MethodSource("invalidNumbersSizeTestCases")
    @ParameterizedTest(name = "로또 번호: {0}")
    void 생성한_번호의_개수가_맞지_않으면_오류가_발생한다(List<Integer> invalidNumbers) {
        List<Integer> fakedNumbers = FakeRandomGenerator.from(invalidNumbers).generate();

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Lotto.createLotto(fakedNumbers);
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
