package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinnerLotto;
import lotto.domain.lottoMachine.FakeRandomGenerator;
import lotto.util.NumbersSeparator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinnerMachineTest {
    WinnerMachine winnerMachine;

    @BeforeEach
    void setUp() {
        List<Integer> fakeRandomNumbers = FakeRandomGenerator.of(List.of(1, 2, 3, 4, 5, 6), 7).generate();
        Lotto basicNumbers = NumbersSeparator.separateLottoNumbers(fakeRandomNumbers);
        BonusNumber bonusNumber = NumbersSeparator.separateBonusNumber(fakeRandomNumbers);
        WinnerLotto winnerLotto = WinnerLotto.of(basicNumbers, bonusNumber);
        winnerMachine = WinnerMachine.from(winnerLotto);
    }


    @Test
    void 로또_결과_정상_흐름() {
        List<Lotto> lottos = getLottos();
        List<Result> expectedResults = List.of(Result.FIRST, Result.SECOND, Result.THIRD, Result.FOURTH, Result.FIFTH,
                Result.NOTHING, Result.NOTHING, Result.NOTHING);
        List<Result> results = winnerMachine.checkLottos(lottos);

        Assertions.assertThat(results)
                .isEqualTo(expectedResults);
    }

    private List<Lotto> getLottos() {
        List<Lotto> lottos = new ArrayList<>();
        List<Integer> lotto1 = List.of(1, 2, 3, 4, 5, 6); // 1등
        List<Integer> lotto2 = List.of(1, 2, 3, 4, 5, 7); // 2등
        List<Integer> lotto3 = List.of(2, 3, 4, 5, 6, 8); // 3등
        List<Integer> lotto4 = List.of(3, 4, 5, 6, 7, 8); // 4등
        List<Integer> lotto5 = List.of(4, 5, 6, 8, 9, 10); // 5등
        List<Integer> lotto6 = List.of(5, 6, 7, 8, 9, 10); // 꼴등
        List<Integer> lotto7 = List.of(6, 7, 8, 9, 10, 11); // 꼴등
        List<Integer> lotto8 = List.of(7, 8, 9, 10, 11, 12); // 꼴등
        addBuyLotto(lottos, lotto1);
        addBuyLotto(lottos, lotto2);
        addBuyLotto(lottos, lotto3);
        addBuyLotto(lottos, lotto4);
        addBuyLotto(lottos, lotto5);
        addBuyLotto(lottos, lotto6);
        addBuyLotto(lottos, lotto7);
        addBuyLotto(lottos, lotto8);
        return lottos;
    }

    private void addBuyLotto(List<Lotto> lottos, List<Integer> lotto) {
        List<Integer> buyNumbers = FakeRandomGenerator.from(lotto).generate();
        Lotto basicNumbers = NumbersSeparator.separateLottoNumbers(buyNumbers);
        lottos.add(basicNumbers);
    }
}
