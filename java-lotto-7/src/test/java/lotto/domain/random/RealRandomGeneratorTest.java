package lotto.domain.random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

class RealRandomGeneratorTest {
    @RepeatedTest(10)
    void 랜덤번호_7개가_생성된다() {
        LottoMachine lottoMachine = new RealRandomGenerator();
        Assertions.assertDoesNotThrow(lottoMachine::generate);
    }
}
