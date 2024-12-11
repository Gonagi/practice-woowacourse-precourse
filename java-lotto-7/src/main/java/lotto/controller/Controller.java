package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Calculator;
import lotto.domain.Result;
import lotto.domain.WinnerMachine;
import lotto.domain.lotto.BasicNumbers;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.Lotto;
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
        int money = inputMoneyProcess();
        List<BasicNumbers> purchasedLottos = purchasedLottosProcess(money);
        WinnerMachine winnerMachine = createWinnerMachine();

        checkLottosByWinnerMachine(winnerMachine, purchasedLottos);
    }

    private int inputMoneyProcess() {
        try {
            int money = inputview.inputMoney();
            System.out.println();
            return money;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputMoneyProcess();
        }
        return 0;
    }

    private List<BasicNumbers> purchasedLottosProcess(int money) {
        List<BasicNumbers> purchasedLottos = purchaseLottos(money);
        outputView.printPurchaseLottos(purchasedLottos);
        System.out.println();
        return purchasedLottos;
    }

    private WinnerMachine createWinnerMachine() {
        BasicNumbers winningBasicNumbers = inputWinningBasicNumbersProcess();
        BonusNumber winningBonusNumber = inputWinningBonusNumberProcess();
        return WinnerMachine.from(Lotto.of(winningBasicNumbers, winningBonusNumber));
    }

    private void checkLottosByWinnerMachine(WinnerMachine winnerMachine, List<BasicNumbers> purchasedLottos) {
        List<Result> results = winnerMachine.checkLottos(purchasedLottos);
        outputView.printLottoResults(results);
        outputView.printRateOfRange(Calculator.calculateRateOfReturn(results));
    }


    private BasicNumbers inputWinningBasicNumbersProcess() {
        try {
            String inputNumbers = inputview.inputWinnerBasicNumbers();
            List<Integer> winningBasicNumbers = FakeRandomGenerator.from(
                    NumbersSeparator.splitWinningBasicNumbers(inputNumbers)).generate();
            System.out.println();
            return BasicNumbers.from(winningBasicNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputWinningBasicNumbersProcess();
        }
        return null;
    }


    private BonusNumber inputWinningBonusNumberProcess() {
        try {
            int bonusNumber = inputview.inputWinningBonusNumber();
            System.out.println();
            return BonusNumber.from(bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputWinningBonusNumberProcess();
        }
        return null;
    }

    public List<BasicNumbers> purchaseLottos(final int money) {
        List<BasicNumbers> purchasedLottos = new ArrayList<>();
        for (int count = 0; count < money / 1000; count++) {
            List<Integer> randomNumbers = lottoMachine.generate();
            purchasedLottos.add(BasicNumbers.from(randomNumbers));
        }

        return purchasedLottos;
    }

}