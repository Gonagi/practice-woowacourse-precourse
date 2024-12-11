package lotto.config;

import lotto.controller.Controller;
import lotto.domain.lottoMachine.LottoMachine;
import lotto.domain.lottoMachine.RealRandomGenerator;
import lotto.view.Inputview;
import lotto.view.OutputView;

public class AppConfig {
    private final Inputview inputview;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public AppConfig() {
        this.inputview = inputview();
        this.outputView = outputView();
        this.lottoMachine = lottoMachine();
    }

    public Controller controller() {
        return new Controller(inputview, outputView, lottoMachine);
    }

    public Inputview inputview() {
        return new Inputview();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LottoMachine lottoMachine() {
        return new RealRandomGenerator();
    }
}
