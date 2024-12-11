package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Result;
import lotto.domain.WinnerMachine;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinnerLotto;
import lotto.domain.lottoMachine.FakeRandomGenerator;
import lotto.domain.lottoMachine.LottoMachine;
import lotto.util.NumbersSeparator;
import lotto.view.Inputview;
import lotto.view.OutputView;

public class Controller {
    private final Inputview inputview;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public Controller(final Inputview inputview, final OutputView outputView, final LottoMachine lottoMachine) {
        this.inputview = inputview;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        int money = retryOnException(inputview::inputMoney);
        List<Lotto> purchasedLottos = purchasedLottosProcess(money);
        WinnerMachine winnerMachine = createWinnerMachine();

        checkLottosByWinnerMachine(winnerMachine, purchasedLottos);
    }

    private List<Lotto> purchasedLottosProcess(int money) {
        List<Lotto> purchasedLottos = purchaseLottos(money);
        outputView.printPurchaseLottos(purchasedLottos);
        return purchasedLottos;
    }

    private WinnerMachine createWinnerMachine() {
        Lotto winningBasicNumbers = retryOnException(this::inputWinningBasicNumbersProcess);
        BonusNumber winningBonusNumber = retryOnException(this::inputWinningBonusNumberProcess);
        return WinnerMachine.from(WinnerLotto.of(winningBasicNumbers, winningBonusNumber));
    }

    private void checkLottosByWinnerMachine(final WinnerMachine winnerMachine, final List<Lotto> purchasedLottos) {
        List<Result> results = winnerMachine.checkLottos(purchasedLottos);
        outputView.printLottoResults(results);
        outputView.printRateOfRange(Result.calculateRateOfReturn(results));
    }

    private Lotto inputWinningBasicNumbersProcess() {
        String inputNumbers = inputview.inputWinnerBasicNumbers();
        List<Integer> winningBasicNumbers = FakeRandomGenerator.from(
                NumbersSeparator.splitWinningLottoInput(inputNumbers)).generate();
        return Lotto.from(winningBasicNumbers);
    }

    private BonusNumber inputWinningBonusNumberProcess() {
        int bonusNumber = inputview.inputWinningBonusNumber();
        return BonusNumber.from(bonusNumber);
    }

    private List<Lotto> purchaseLottos(final int money) {
        List<Lotto> purchasedLottos = new ArrayList<>();
        for (int count = 0; count < money / 1000; count++) {
            List<Integer> randomNumbers = lottoMachine.generate();
            purchasedLottos.add(Lotto.from(randomNumbers));
        }
        return purchasedLottos;
    }

    private <T> T retryOnException(final Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
