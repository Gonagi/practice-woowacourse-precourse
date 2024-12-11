package lotto.domain;

import static lotto.constants.Messages.NON_ENOUGH_MONEY;
import static lotto.constants.Messages.NON_THOUSAND_UNIT_MONEY;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.BasicNumbers;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottoMachine.LottoMachine;
import lotto.domain.lottoMachine.RealRandomGenerator;
import lotto.util.NumbersSeparator;

public class Store {
    private final LottoMachine lottoMachine;

    public Store() {
        this.lottoMachine = new RealRandomGenerator();
    }

    public List<Lotto> buyLottos(final int money) {
        validateMoney(money);

        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < money / 1000; count++) {
            createLottos(lottos);
        }
        return lottos;
    }

    private void createLottos(List<Lotto> lottos) {
        List<Integer> randomNumbers = lottoMachine.generate();
        BasicNumbers lotto = NumbersSeparator.separateLottoNumbers(randomNumbers);
        BonusNumber bonusNumber = NumbersSeparator.separateBonusNumber(randomNumbers);

        lottos.add(Lotto.of(lotto, bonusNumber));
    }

    private void validateMoney(final int money) {
        validateUnderZeroMoney(money);
        validate1000UnitMoney(money);
    }

    private void validateUnderZeroMoney(final int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(NON_ENOUGH_MONEY.getMessage());
        }
    }

    private void validate1000UnitMoney(final int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(NON_THOUSAND_UNIT_MONEY.getMessage());
        }
    }
}
