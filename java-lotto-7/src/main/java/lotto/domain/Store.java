package lotto.domain;

import static lotto.constants.Messages.NON_ENOUGH_MONEY;
import static lotto.constants.Messages.NON_THOUSAND_UNIT_MONEY;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Receipt;
import lotto.domain.lottoMachine.LottoMachine;
import lotto.domain.lottoMachine.RealRandomGenerator;
import lotto.util.NumbersSeparator;

public class Store {
    private final LottoMachine lottoMachine;

    public Store() {
        this.lottoMachine = new RealRandomGenerator();
    }

    public List<Receipt> buyLottos(final int money) {
        validateMoney(money);

        List<Receipt> receipts = new ArrayList<>();
        for (int count = 0; count < money / 1000; count++) {
            createReceipt(receipts);
        }
        return receipts;
    }

    private void createReceipt(List<Receipt> receipts) {
        List<Integer> randomNumbers = lottoMachine.generate();
        Lotto lotto = NumbersSeparator.separateLottoNumbers(randomNumbers);
        int bonusNumber = NumbersSeparator.separateBonusNumber(randomNumbers);
        
        receipts.add(Receipt.of(lotto, BonusNumber.from(bonusNumber)));
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
