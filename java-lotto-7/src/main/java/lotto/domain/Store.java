package lotto.domain;

import static lotto.constants.Messages.NON_ENOUGH_MONEY;
import static lotto.constants.Messages.NON_THOUSAND_UNIT_MONEY;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.random.LottoMachine;
import lotto.domain.random.RealRandomGenerator;

public class Store {
    private final LottoMachine lottoMachine;

    public Store() {
        this.lottoMachine = new RealRandomGenerator();
    }

    public List<Lotto> buyLottos(final int money) {
        validateMoney(money);

        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < money / 1000; count++) {
            lottos.add(Lotto.createLotto(lottoMachine.generate()));
        }
        return lottos;
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
