package lotto.domain.lotto;

import java.util.List;

public class WinnerLotto {
    private final Lotto winninglotto;
    private final BonusNumber bonusNumber;

    private WinnerLotto(final Lotto winninglotto, final BonusNumber bonusNumber) {
        this.winninglotto = winninglotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinnerLotto of(final Lotto winninglotto, final BonusNumber bonusNumber) {
        return new WinnerLotto(winninglotto, bonusNumber);
    }

//    public List<Integer> getLottoNumbers() {
//        List<Integer> lottoNumbers = new ArrayList<>(winninglotto.getNumbers());
//        lottoNumbers.add(bonusNumber.getNumber());
//        return lottoNumbers;
//    }

    public List<Integer> getBasicNumbers() {
        return winninglotto.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }
}
