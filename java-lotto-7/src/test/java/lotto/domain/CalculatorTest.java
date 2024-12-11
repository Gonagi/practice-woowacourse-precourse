package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    void 백분율_계산기_정상_흐름() {
        String expectedReturnOfRate = "50000125.00";
        List<Result> results = new ArrayList<>(List.of(Result.FIRST, Result.FIFTH, Result.NOTHING, Result.NOTHING));

        Assertions.assertThat(Calculator.calculateRateOfReturn(results))
                .isEqualTo(expectedReturnOfRate);
    }

}
