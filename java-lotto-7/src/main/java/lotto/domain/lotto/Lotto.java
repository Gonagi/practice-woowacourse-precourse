package lotto.domain.lotto;

import java.util.List;

public class Lotto {
    private final BasicNumbers lotto;
    private final BonusNumber bonusNumber;

    private Lotto(final BasicNumbers lotto, final BonusNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static Lotto of(final BasicNumbers lotto, final BonusNumber bonusNumber) {
        return new Lotto(lotto, bonusNumber);
    }

    public List<Integer> getBasicNumbers() {
        return lotto.getBasicNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }
}
