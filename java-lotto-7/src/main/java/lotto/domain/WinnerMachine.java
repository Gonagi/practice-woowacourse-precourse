package lotto.domain;

import java.util.List;
import java.util.function.Predicate;
import lotto.domain.lotto.BasicNumbers;
import lotto.domain.lotto.Lotto;

public class WinnerMachine {
    private final Lotto winnerLotto;

    private WinnerMachine(final Lotto winnerLotto) {
        this.winnerLotto = winnerLotto;
    }

    public static WinnerMachine from(final Lotto winnerLotto) {
        return new WinnerMachine(winnerLotto);
    }

    public List<Result> checkLottos(final List<BasicNumbers> lottos) {
        return lottos.stream()
                .map(this::checkLotto)
                .toList();
    }

    private Result checkLotto(final BasicNumbers lotto) {
        int basicMatchCount = checkBasicMatchCount(lotto.getBasicNumbers());
        boolean bonusMatch = checkBonusMatch(lotto.getBasicNumbers());
        return Result.of(basicMatchCount, bonusMatch);
    }

    private int checkBasicMatchCount(final List<Integer> lottoNumbers) {
        List<Integer> winnerLottoLottoNumbers = winnerLotto.getBasicNumbers();

        return (int) lottoNumbers.stream()
                .filter(matchList -> winnerLottoLottoNumbers.stream()
                        .anyMatch(Predicate.isEqual(matchList))).count();
    }

    private boolean checkBonusMatch(final List<Integer> lottoNumbers) {
        int winnerLottoBonusNumber = winnerLotto.getBonusNumber();
        return lottoNumbers.contains(winnerLottoBonusNumber);
    }
}
