package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.lottoMachine.FakeRandomGenerator;
import lotto.util.NumbersSeparator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 기본_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            List<Integer> fakeLottoNumbers = FakeRandomGenerator.from(List.of(1, 2, 3, 4, 5, 6, 7)).generate();
            BasicNumbers.from(fakeLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호를_반환한다() {
        List<Integer> expectedLottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        List<Integer> fakedLottoNumbers = FakeRandomGenerator.of(List.of(1, 2, 3, 4, 5, 6), 7).generate();

        Assertions.assertThat(fakedLottoNumbers)
                .isEqualTo(expectedLottoNumbers);
    }

    @Test
    void 기본_번호를_반환한다() {
        List<Integer> expectedBasicNumbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> fakedLottoNumbers = FakeRandomGenerator.of(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        BasicNumbers basicNumbers = NumbersSeparator.separateLottoNumbers(fakedLottoNumbers);

        Assertions.assertThat(basicNumbers.getBasicNumbers())
                .isEqualTo(expectedBasicNumbers);
    }

    @Test
    void 보너스_번호를_반환한다() {
        int expectedBonusNumber = 7;

        List<Integer> fakedLottoNumbers = FakeRandomGenerator.of(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        BonusNumber bonusNumber = NumbersSeparator.separateBonusNumber(fakedLottoNumbers);

        Assertions.assertThat(bonusNumber.getNumber())
                .isEqualTo(expectedBonusNumber);
    }
}
