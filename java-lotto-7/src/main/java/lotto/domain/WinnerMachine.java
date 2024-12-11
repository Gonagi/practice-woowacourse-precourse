package lotto.domain;

import java.util.List;
import java.util.function.Predicate;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinnerLotto;

public class WinnerMachine {
    private final WinnerLotto winnerLotto;

    private WinnerMachine(final WinnerLotto winnerLotto) {
        this.winnerLotto = winnerLotto;
    }

    public static WinnerMachine from(final WinnerLotto winnerLotto) {
        return new WinnerMachine(winnerLotto);
    }

    public List<Result> checkLottos(final List<Lotto> lottos) {
        return lottos.stream()
                .map(this::checkLotto)
                .toList();
    }

    private Result checkLotto(final Lotto lotto) {
        int basicMatchCount = checkBasicMatchCount(lotto.getNumbers());
        boolean bonusMatch = checkBonusMatch(lotto.getNumbers());
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
