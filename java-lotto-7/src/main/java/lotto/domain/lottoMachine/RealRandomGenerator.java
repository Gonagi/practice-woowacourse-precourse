package lotto.domain.lottoMachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RealRandomGenerator implements LottoMachine {
    public RealRandomGenerator() {
    }

    @Override
    public List<Integer> generate() {
        List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 7);
        validateLottoNumbers(uniqueNumbers);
        return uniqueNumbers;
    }
}
