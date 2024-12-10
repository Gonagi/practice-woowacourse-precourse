package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.random.FakeRandomGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> FakeRandomGenerator.from(List.of(1, 1, 2, 3, 4, 5, 6), 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> FakeRandomGenerator.from(List.of(1, 2, 3, 4, 5, 5), 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호를_반환한다() {
        List<Integer> expectedLottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> lottoNumbers = FakeRandomGenerator.from(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        Lotto lotto = Lotto.createLotto(lottoNumbers);

        Assertions.assertThat(lotto.getLottoNumbers())
                .isEqualTo(expectedLottoNumbers);
    }

    @Test
    void 보너스_번호를_반환한다() {
        int expectedBonusNumber = 7;

        List<Integer> lottoNumbers = FakeRandomGenerator.from(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        Lotto lotto = Lotto.createLotto(lottoNumbers);

        Assertions.assertThat(lotto.getBonusNumber())
                .isEqualTo(expectedBonusNumber);
    }
}
