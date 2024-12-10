package lotto.domain.lotto;

import java.util.List;

public class Receipt {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    private Receipt(final Lotto lotto, final BonusNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static Receipt of(final Lotto lotto, final BonusNumber bonusNumber) {
        return new Receipt(lotto, bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lotto.getLottoNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }
}
