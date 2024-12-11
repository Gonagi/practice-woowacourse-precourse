package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final BasicNumbers basicNumbers;
    private final BonusNumber bonusNumber;

    private Lotto(final BasicNumbers lotto, final BonusNumber bonusNumber) {
        this.basicNumbers = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static Lotto of(final BasicNumbers lotto, final BonusNumber bonusNumber) {
        return new Lotto(lotto, bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(basicNumbers.getBasicNumbers());
        lottoNumbers.add(bonusNumber.getNumber());
        return lottoNumbers;
    }

    public List<Integer> getBasicNumbers() {
        return basicNumbers.getBasicNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }
}
